#!/bin/bash
# Double-click this file to launch the APCS Challenge Problem Grader.

# Change to the directory this script lives in
cd "$(dirname "$0")"

echo ""
echo "  ╔══════════════════════════════════════╗"
echo "  ║   APCS Challenge Problem Grader      ║"
echo "  ╚══════════════════════════════════════╝"
echo ""

# ── Check prerequisites ────────────────────────────────────────────────────
if ! command -v python3 &>/dev/null; then
  echo "  ✗ python3 not found. Please install Python 3."
  read -p "  Press Enter to close."
  exit 1
fi

if ! command -v javac &>/dev/null; then
  echo "  ✗ javac not found. Please install the Java JDK."
  read -p "  Press Enter to close."
  exit 1
fi

# ── Free port 3000 if something is already using it ───────────────────────
if lsof -ti :3000 &>/dev/null; then
  echo "  Stopping previous grader instance..."
  lsof -ti :3000 | xargs kill -9 2>/dev/null
  sleep 0.5
fi

# ── Open browser once the server is ready ─────────────────────────────────
(
  for i in {1..20}; do
    sleep 0.5
    if curl -s http://localhost:3000/health &>/dev/null; then
      open http://localhost:3000
      break
    fi
  done
) &

# ── Start server (runs in foreground — closing this window stops it) ───────
echo "  Starting server..."
echo "  → http://localhost:3000"
echo ""
echo "  Close this window (or press Ctrl+C) to stop the grader."
echo ""
python3 server.py
