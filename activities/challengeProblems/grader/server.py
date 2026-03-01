#!/usr/bin/env python3
"""
APCS Challenge Problem Grader
Run:  python3 server.py
Then open http://localhost:3000 in a browser.
"""

import cgi
import html
import json
import os
import re
import shutil
import subprocess
import sys
import tempfile
import threading
from http.server import BaseHTTPRequestHandler, HTTPServer
from pathlib import Path

PORT = 3000
PUBLIC = Path(__file__).parent / "public"

# ─── Test case definitions ────────────────────────────────────────────────────

DNA = {
    "bob":     "AGATCAGATCAGATCAGATCTTTTTTCTAATGAATGAATGAATGAATGTCTAGTCTAG",
    "charlie": "AGATCAGATCAGATCTTTTTTCTTTTTTTCTAATGAATGAATGAATGAATGAATGTCTAGTCTAGTCTAGTCTAG",
    "diana":   "AGATCAGATCAGATCAGATCAGATCAGATCTTTTTTCTTTTTTTCTTTTTTTCTTTTTTTCTAATGAATGTCTAGTCTAGTCTAG",
    "eve":     "AGATCTTTTTTCTTTTTTTCTTTTTTTCTTTTTTTCTTTTTTTCTTTTTTTCTTTTTTTCTAATGAATGAATGAATGTCTAGTCTAGTCTAGTCTAGTCTAG",
    "nomatch": "AGATCAGATCAGATCTTTTTTCTTTTTTTCTAATGAATGTCTAGTCTAG",
}

RUNOFF1 = "3\nAlice\nBob\nCharlie\n5\nAlice Bob Charlie\nCharlie Alice Bob\nBob Charlie Alice\nAlice Charlie Bob\nCharlie Bob Alice\n"
RUNOFF2 = "2\nAlice\nBob\n4\nAlice Bob\nAlice Bob\nBob Alice\nBob Alice\n"
RUNOFF3 = "1\nSolo\n3\nSolo\nSolo\nSolo\n"

DICT_WORDS = [
    "the","and","to","of","a","in","is","it","you","that",
    "he","was","for","on","are","as","with","his","they","at",
    "be","this","from","or","one","have","an","by","but","not",
    "had","her","she","all","there","been","when","who","will",
    "would","said","each","which","do","their","what","so","up",
]

PROBLEMS = [
    None,  # index 0 unused

    # 1 — Readability
    {
        "id": 1, "name": "Readability 2.0",
        "subtitle": "Coleman–Liau Grade Level Calculator",
        "tests": [
            {"description": "Short children's passage",
             "input": "Congratulations! Today is your day. You're off to Great Places! You're off and away!",
             "expected": "Grade 3", "check": "exact"},
            {"description": "Descriptive sentence with longer words — Grade 10",
             "input": "There were many bright yellow stars scattered across the dark empty canvas of the night sky.",
             "expected": "Grade 10", "check": "exact"},
            {"description": "Very short input",
             "input": "Go.",
             "expected": "Before Grade 1", "check": "exact"},
            {"description": "Dense academic text",
             "input": "A large class of computational problems involve the determination of properties of graphs, digraphs, integers, arrays of integers, finite families of finite sets, boolean formulas and elements of other combinatorial domains.",
             "expected": "Grade 16+", "check": "exact"},
        ],
    },

    # 2 — Credit Card
    {
        "id": 2, "name": "Credit Card Validator",
        "subtitle": "Luhn's Algorithm + Card Type Detection",
        "tests": [
            {"description": "Valid VISA (16 digits, starts with 4)",
             "input": "4003600000000014", "expected": "VISA", "check": "exact"},
            {"description": "Valid AMEX (15 digits, starts with 37)",
             "input": "378282246310005", "expected": "AMEX", "check": "exact"},
            {"description": "Valid MASTERCARD (16 digits, starts with 51)",
             "input": "5105105105105100", "expected": "MASTERCARD", "check": "exact"},
            {"description": "Invalid checksum",
             "input": "1234567890123456", "expected": "INVALID", "check": "exact"},
            {"description": "Contains hyphens — reject immediately",
             "input": "4003-6000-0000-0014", "expected": "INVALID", "check": "exact"},
            {"description": "Valid Discover card — unknown type → VALID",
             "input": "6011111111111117", "expected": "VALID", "check": "exact"},
        ],
    },

    # 3 — Cipher Cracker
    {
        "id": 3, "name": "Cipher Cracker",
        "subtitle": "Frequency Analysis + Hill Climbing",
        "approximate": True,
        "tests": [
            {"description": "Sample ciphertext — check format and decoding quality",
             "input": "Zsi bpnn vf eyxvdm vf bvzs bpm jxmibmiz xvlpmi bpib wvl kwevlz, ib bpm sbvxa nwcm vf bpmq.",
             "expected": None,
             "expectedDisplay": "Output contains \"Mapping:\" + \"Decoded:\" headers and ≥ 5 dictionary words",
             "check": "cipherCracker"},
        ],
    },

    # 4 — Recover
    {
        "id": 4, "name": "Recover",
        "subtitle": "Hidden Message in 2D Intensity Grid",
        "tests": [
            {"description": "Decode hard-coded 5×10 sample grid → AB",
             "input": "",
             "expected": "AB",
             "expectedDisplay": "Output contains \"AB\"",
             "check": "contains"},
        ],
    },

    # 5 — DNA Matching
    {
        "id": 5, "name": "DNA Matching",
        "subtitle": "STR Longest-Run Analysis",
        "tests": [
            {"description": "Bob's STR profile: AGATC×4, TTTTTTCT×1, AATG×5, TCTAG×2",
             "input": DNA["bob"], "expected": "Bob", "check": "exact"},
            {"description": "Charlie's STR profile: AGATC×3, TTTTTTCT×2, AATG×6, TCTAG×4",
             "input": DNA["charlie"], "expected": "Charlie", "check": "exact"},
            {"description": "Diana's STR profile: AGATC×6, TTTTTTCT×4, AATG×2, TCTAG×3",
             "input": DNA["diana"], "expected": "Diana", "check": "exact"},
            {"description": "Eve's STR profile: AGATC×1, TTTTTTCT×7, AATG×4, TCTAG×5",
             "input": DNA["eve"], "expected": "Eve", "check": "exact"},
            {"description": "Profile {3,2,2,2} — no database match",
             "input": DNA["nomatch"], "expected": "No match", "check": "exact"},
        ],
    },

    # 6 — Runoff
    {
        "id": 6, "name": "Runoff",
        "subtitle": "Ranked-Choice Election Simulator",
        "tests": [
            {"description": "Charlie wins after Bob is eliminated (3 candidates, 5 voters)",
             "input": RUNOFF1, "expected": "Charlie", "check": "exact"},
            {"description": "Tie: Alice and Bob each get 2/4 votes",
             "input": RUNOFF2, "expected": ["Alice", "Bob"],
             "expectedDisplay": "Output contains both \"Alice\" and \"Bob\"",
             "check": "containsAll"},
            {"description": "Single candidate wins immediately (100%)",
             "input": RUNOFF3, "expected": "Solo", "check": "exact"},
        ],
    },

    # 7 — Speller Lite
    {
        "id": 7, "name": "Speller Lite",
        "subtitle": "Binary Search Spell Checker (No HashSet)",
        "tests": [
            {"description": "\"The quick brown fox…\" — 6 misspellings",
             "input": "The quick brown fox jumps over the lazy dog.",
             "expected": ["MISSPELLED WORDS","brown","dog","fox","jumps","lazy","quick"],
             "expectedDisplay": "Output contains: MISSPELLED WORDS, brown, dog, fox, jumps, lazy, quick",
             "check": "containsAll"},
            {"description": "\"She said he was going to find the answer.\" — 2 misspellings",
             "input": "She said he was going to find the answer.",
             "expected": ["MISSPELLED WORDS","going","answer"],
             "expectedDisplay": "Output contains: MISSPELLED WORDS, going, answer",
             "check": "containsAll"},
            {"description": "Quoted text with punctuation stripping",
             "input": '"Hello," said the little girl, who had never been to such a place before.',
             "expected": ["MISSPELLED WORDS","hello","girl"],
             "expectedDisplay": "Output contains: MISSPELLED WORDS, hello, girl",
             "check": "containsAll"},
        ],
    },

    # 8 — Minesweeper
    {
        "id": 8, "name": "Minesweeper",
        "subtitle": "Clue Generator + Connected Zero Regions",
        "tests": [
            {"description": "4×4 Sample 1 clue grid (mines at row 0 col 2, row 2 col 1)",
             "input": "",
             "expected": ["1 1 * 1","1 2 2 1","1 * 2 0","1 1 2 0"],
             "expectedDisplay": "Output contains all 4 clue rows: \"1 1 * 1\", \"1 2 2 1\", \"1 * 2 0\", \"1 1 2 0\"",
             "check": "containsAll"},
        ],
    },
]

# ─── Java compilation + execution ─────────────────────────────────────────────

def compile_java_files(java_files: list) -> dict:
    """Compile one or more .java files together. Returns {'ok': bool, 'stderr': str}"""
    try:
        result = subprocess.run(
            ["javac"] + java_files,
            capture_output=True, text=True, timeout=30
        )
        return {"ok": result.returncode == 0, "stderr": result.stderr}
    except subprocess.TimeoutExpired:
        return {"ok": False, "stderr": "javac timed out"}
    except FileNotFoundError:
        return {"ok": False, "stderr": "javac not found — is the JDK installed?"}


def run_java(tmp_dir: str, class_name: str, stdin_input: str, timeout: int = 5) -> dict:
    """Returns {'stdout': str, 'stderr': str, 'exit_code': int, 'timed_out': bool}"""
    try:
        result = subprocess.run(
            ["java", "-cp", tmp_dir, class_name],
            input=stdin_input,
            capture_output=True, text=True, timeout=timeout
        )
        return {
            "stdout": result.stdout.strip(),
            "stderr": result.stderr.strip(),
            "exit_code": result.returncode,
            "timed_out": False,
        }
    except subprocess.TimeoutExpired:
        return {"stdout": "", "stderr": "Timed out (> 5 s)", "exit_code": -1, "timed_out": True}
    except Exception as e:
        return {"stdout": "", "stderr": str(e), "exit_code": -1, "timed_out": False}


# ─── Grading logic ────────────────────────────────────────────────────────────

def grade_test(test: dict, run: dict) -> dict:
    inp = test.get("input", "") or ""
    base = {
        "description":     test["description"],
        "inputDisplay":    inp[:80] + ("…" if len(inp) > 80 else "") if inp else "(none)",
        "expectedDisplay": test.get("expectedDisplay") or str(test.get("expected", "")),
        "actual":          run["stdout"],
        "timedOut":        run["timed_out"],
    }

    if run["timed_out"]:
        return {**base, "pass": False, "note": "Program timed out (> 5 s). Check for infinite loops."}

    if run["exit_code"] != 0 and not run["stdout"]:
        first_line = (run["stderr"] or "unknown error").split("\n")[0]
        return {**base, "pass": False, "note": f"Runtime error: {first_line}"}

    actual = run["stdout"]
    check  = test["check"]

    if check == "exact":
        ok = actual == str(test["expected"])
        return {**base, "pass": ok, "note": "" if ok else f'Expected "{test["expected"]}"'}

    if check == "contains":
        ok = str(test["expected"]) in actual
        return {**base, "pass": ok, "note": "" if ok else f'Output should contain "{test["expected"]}"'}

    if check == "containsAll":
        missing = [s for s in test["expected"] if s not in actual]
        ok = len(missing) == 0
        note = "" if ok else "Missing from output: " + ", ".join(f'"{s}"' for s in missing)
        return {**base, "pass": ok, "note": note}

    if check == "cipherCracker":
        if "Mapping:" not in actual:
            return {**base, "pass": False, "note": 'Output does not contain "Mapping:" header'}
        if "Decoded:" not in actual:
            return {**base, "pass": False, "note": 'Output does not contain "Decoded:" header'}
        decoded_start = actual.find("Decoded:") + len("Decoded:")
        decoded_text  = actual[decoded_start:].strip()
        found = [w for w in DICT_WORDS if re.search(r'\b' + w + r'\b', decoded_text, re.IGNORECASE)]
        score = len(found)
        ok    = score >= 5
        sample = ", ".join(found[:8]) + ("…" if len(found) > 8 else "")
        note  = f"Format ✓  |  Dictionary words found: {score}/47 (need ≥ 5)"
        if found:
            note += f"  |  Found: {sample}"
        return {**base, "pass": ok, "note": note}

    return {**base, "pass": False, "note": f"Unknown check type: {check}"}


def grade_files(problem_id: int, sources: dict) -> dict:
    """
    sources: {filename: java_source_string}  — one or more .java files.
    All files are written to a temp dir and compiled together, so helper
    classes are available. The class that contains `main` is run.
    """
    problem = PROBLEMS[problem_id]

    # Strip package declarations from every file and build a clean name→source map
    cleaned = {}
    for filename, src in sources.items():
        src = re.sub(r'^\s*package\s+[\w.]+\s*;\s*\r?\n?', '', src, flags=re.MULTILINE)
        # Derive the expected filename from the public class name in this file
        m = re.search(r'public\s+class\s+(\w+)', src)
        if m:
            cleaned[m.group(1) + ".java"] = src
        else:
            # No public class — keep the original filename (helper/package-private classes)
            cleaned[filename] = src

    if not cleaned:
        return {"error": "Could not find any public class declaration in your uploaded files."}

    # Find which class has main() — that's the entry point
    main_class = None
    main_re = re.compile(r'public\s+static\s+(?:final\s+)?void\s+main\s*\(')
    for fname, src in cleaned.items():
        if main_re.search(src):
            m = re.search(r'public\s+class\s+(\w+)', src)
            if m:
                main_class = m.group(1)
                break

    if main_class is None:
        # Fall back: use the first public class found
        for fname, src in cleaned.items():
            m = re.search(r'public\s+class\s+(\w+)', src)
            if m:
                main_class = m.group(1)
                break

    if main_class is None:
        return {"error": "Could not locate a main() method in any of your uploaded files."}

    # Write all files and compile together
    tmp_dir = tempfile.mkdtemp(prefix="apcs-")
    try:
        java_files = []
        for fname, src in cleaned.items():
            path = os.path.join(tmp_dir, fname)
            with open(path, "w", encoding="utf-8") as f:
                f.write(src)
            java_files.append(path)

        # Compile everything at once so helper classes are available
        compile_result = compile_java_files(java_files)
        if not compile_result["ok"]:
            file_list = ", ".join(cleaned.keys())
            return {"compilationError": compile_result["stderr"],
                    "className": main_class,
                    "fileList": file_list}

        # Run each test using the detected main class
        results = []
        for test in problem["tests"]:
            run = run_java(tmp_dir, main_class, test.get("input", "") or "")
            results.append(grade_test(test, run))

        passed = sum(1 for r in results if r["pass"])
        file_list = ", ".join(cleaned.keys())
        return {"results": results, "passed": passed, "total": len(results),
                "className": main_class, "fileList": file_list}

    finally:
        shutil.rmtree(tmp_dir, ignore_errors=True)


# ─── HTTP server ──────────────────────────────────────────────────────────────

class Handler(BaseHTTPRequestHandler):

    def log_message(self, fmt, *args):
        # Suppress default logging; print a simpler version
        print(f"  {self.command} {self.path} → {args[1] if len(args) > 1 else ''}")

    def send_json(self, data, status=200):
        body = json.dumps(data).encode("utf-8")
        self.send_response(status)
        self.send_header("Content-Type", "application/json")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def serve_file(self, filepath):
        ext = os.path.splitext(filepath)[1].lower()
        types = {".html": "text/html", ".css": "text/css", ".js": "application/javascript",
                 ".png": "image/png", ".ico": "image/x-icon"}
        mime = types.get(ext, "application/octet-stream")
        try:
            with open(filepath, "rb") as f:
                data = f.read()
            self.send_response(200)
            self.send_header("Content-Type", mime)
            self.send_header("Content-Length", str(len(data)))
            self.end_headers()
            self.wfile.write(data)
        except FileNotFoundError:
            self.send_response(404)
            self.end_headers()

    def do_GET(self):
        path = self.path.split("?")[0]

        if path == "/health":
            try:
                r = subprocess.run(["java", "-version"], capture_output=True, text=True, timeout=5)
                ver_text = r.stderr or r.stdout
                m = re.search(r'version "([^"]+)"', ver_text)
                ver = m.group(1) if m else "unknown"
            except Exception:
                ver = "not found"
            self.send_json({"ok": True, "javaVersion": ver})
            return

        if path == "/" or path == "":
            self.serve_file(PUBLIC / "index.html")
            return

        # Static files
        static = PUBLIC / path.lstrip("/")
        if static.is_file():
            self.serve_file(static)
        else:
            self.send_response(404)
            self.end_headers()

    def do_POST(self):
        try:
            self._do_post()
        except Exception as exc:
            import traceback
            traceback.print_exc()
            try:
                self.send_json({"error": f"Server error: {exc}"}, 500)
            except Exception:
                pass

    def _do_post(self):
        m = re.match(r'^/grade/(\d+)$', self.path)
        if not m:
            self.send_json({"error": "Not found"}, 404)
            return

        problem_id = int(m.group(1))
        if problem_id < 1 or problem_id >= len(PROBLEMS):
            self.send_json({"error": f"Unknown problem ID: {problem_id}"}, 400)
            return

        content_type = self.headers.get("Content-Type", "")
        content_length = int(self.headers.get("Content-Length", 0))

        if content_length > 512 * 1024:
            self.send_json({"error": "File too large (max 512 KB)"}, 400)
            return

        body = self.rfile.read(content_length)

        # Parse multipart form data
        # Build a fake environ for cgi.FieldStorage
        import io
        environ = {
            "REQUEST_METHOD": "POST",
            "CONTENT_TYPE": content_type,
            "CONTENT_LENGTH": str(content_length),
        }
        fs = cgi.FieldStorage(
            fp=io.BytesIO(body),
            headers=self.headers,
            environ=environ,
        )

        if "javaFile" not in fs:
            self.send_json({"error": "No file received. Make sure you chose at least one .java file."}, 400)
            return

        # Iterate fs.list directly — more reliable than getlist() for file items
        raw_items = fs.list or []
        items = [it for it in raw_items if it.name == "javaFile"]
        if not items:
            self.send_json({"error": "No javaFile field found in upload."}, 400)
            return

        sources = {}
        for item in items:
            fname = getattr(item, "filename", None) or "Unknown.java"
            if not fname.endswith(".java"):
                self.send_json({"error": f'"{fname}" is not a .java file.'}, 400)
                return
            try:
                src = item.file.read().decode("utf-8", errors="replace")
            except Exception as e:
                self.send_json({"error": f"Could not read {fname}: {e}"}, 400)
                return
            sources[fname] = src

        result = grade_files(problem_id, sources)
        self.send_json(result)


# ─── Entry point ──────────────────────────────────────────────────────────────

if __name__ == "__main__":
    server = HTTPServer(("", PORT), Handler)
    print()
    print("  APCS Challenge Problem Grader")
    print(f"  → http://localhost:{PORT}")
    print()
    print("  Open the URL above in your browser.")
    print("  Press Ctrl+C to stop.")
    print()
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\n  Stopped.")
        sys.exit(0)
