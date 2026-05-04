# Vibe Coding

A 6-class module after the AP exam. You'll spend a week-ish learning to build software with AI, then ship a web app to the internet that's actually yours.

By the end of Day 6 you'll have:
- A live URL anyone can visit
- That project on your public GitHub
- Learned the prompt → run → iterate → commit → push loop of modern AI-driven software development
- Something you'd actually send to someone (hopefully!)

---

## The schedule

| Day | Focus | What you leave with |
|---|---|---|
| 1 | Setup + first deploy | A "Hello, [your name]" page live at your own URL |
| 2 | Build a real app together | A personality quiz of your own theme, deployed |
| 3 | Pick + plan + start your project | A brief, a starting version of your project, deployed |
| 4 | Build day | Must-haves working, deployed |
| 5 | Polish & ship | Demo-ready: named, polished, README'd |
| 6 | Demos | Show your project, play everyone else's |

---

## Before Day 1 (do this at home, ~15 min)

1. **Install Cursor:** [cursor.com](https://cursor.com) → Download. Install like any Mac app.
2. **Make a GitHub account:** [github.com](https://github.com). Use a personal email — you'll keep this through college and into your career. Pick a username you'd be okay seeing on a résumé (your name, not `xX_dragon420_Xx`). This is the URL of your portfolio for the next decade.
3. **Sign in to Cursor with the same email as GitHub.** If you have a school `.edu` email, also visit [cursor.com/students](https://cursor.com/students) for 1 year of Cursor Pro free. Otherwise you'll get a 2-week Pro trial that covers our whole module.

If anything breaks, don't panic. We'll fix it Day 1.

---

## How to think about this

You're not coding the way you did all year in AP CS. You're working *with* an AI as a partner.

**The mental model:** You are a **product manager**. The AI is the **engineer**. You bring:
- What you're building (the goal)
- Who it's for (the user)
- What "good" looks like (the bar)
- The product judgment to say "that's not what I wanted"

The AI brings everything else: the language, the framework, the file structure, the syntax, the libraries.

The single most important rule of vibe coding: describe outcomes, not implementations. Tell the AI what users should see, feel, and do. Let it figure out *how*.

---

## Day 1 — From zero to live URL

The most important hour of the module. Goal: by the end of class, you have a public URL that says "Hello, [your name]" — and every time you push a change, your URL updates within 30 seconds.

What we'll do together:

1. Make sure everyone is signed in to Cursor, GitHub, and Vercel. (Sign in to Vercel with **Continue with GitHub** — fewer accounts.)
2. On github.com → New repo `my-vibe-site` (Public, "Add a README" ✓, Create).
3. In Cursor: `Cmd+Shift+P` → "Git: Clone" → paste the GitHub URL.
4. Open the chat (`Cmd+L`) and prompt:

   > Create an `index.html` that says "Hello, [my name]" in big text on a fun gradient background. Centered, modern look. Plain HTML/CSS in one file.

5. Open `index.html` in a browser. Looks good?
6. **Push to GitHub** from Cursor's Source Control sidebar: stage → commit "first page" → push.
7. **Deploy to Vercel:** vercel.com → Add New Project → Import → `my-vibe-site` → Deploy. URL appears in ~30 sec. Open it. **That's your site, on the internet.**
8. **The magic edit.** Prompt Cursor:

   > Add a button that, when clicked, changes the gradient to a random new color combo.

9. Save → commit → push → watch Vercel auto-deploy → refresh your live URL → it's there.

That's the loop. Everything else this module is variations on it.

---

## Day 2 — Build a real app together

Today we build a personality quiz end-to-end. Everyone copies the same prompts at first, then re-themes the quiz to whatever you want — Marvel hero, dog breed, pizza topping, Hogwarts house, your friend group, anything.

Make a new repo `my-personality-quiz`. Clone, open in Cursor, open chat (`Cmd+L`).

### Prompt 1 — Scaffold

> Build a single-file `index.html` personality quiz called "What kind of pizza are you?".
>
> - 4 questions, each with 4 multiple-choice answers (big tappable buttons).
> - After question 4, score the answers and show ONE of:
>   • Pepperoni — bold, classic, never wrong
>   • Margherita — simple, elegant, always in style
>   • Hawaiian — controversial, loved by some
>   • Pineapple-haters Pineapple — chaotic, confident, has opinions
> - Each result has an emoji and a 1–2 sentence funny description.
> - A "Take it again" button on the result screen.
>
> Style: modern, fun, big colorful buttons, smooth fade between questions.
> Tech: plain HTML, CSS, JavaScript in ONE file. No frameworks, no external libraries.

Read what Cursor produces. You don't need to understand every line — but find the questions array, find where the result is decided, see the shape.

### Prompt 2 — Fix a bug (only if something's broken)

> [Paste the actual error or describe what's broken.]
> Expected: clicking an answer advances to the next question.
> Actual: nothing happens.
> Fix it.

### Prompt 3 — Re-theme

> Change the theme of this quiz from pizza to "[your theme]".
> Update:
> - The title
> - All 4 questions and 16 answers (same structure)
> - The 4 results (new emojis and descriptions)
> - The color palette to match the new vibe
>
> Don't change any of the quiz logic, buttons, or layout.

### Prompt 4 — One stretch (pick one)

> A: Add a progress bar at the top that fills as the user answers.
>
> B: On the result screen, add a "Share my result" button that copies a shareable link to clipboard. Show a "Copied!" toast for 2 seconds.
>
> C: When the result appears, trigger a confetti animation. Use a small inline canvas-based confetti, no external libraries.

Push to GitHub, import to Vercel, get a URL. Text it to a friend. Make them take the quiz. That's a real product, made by you, in one class.

---

## Days 3, 4, 5 — Your project

Three classes building something you actually want to build, in a stack the AI helps you pick.

### 1. Pick something you actually want to build

The best projects come from "I wish ___ existed" or "wouldn't it be funny if ___."

Prompts to find an idea:
- A small tool I wish existed for [my hobby / my homework / my friends]
- A game I'd actually play for 5 minutes
- A page I'd send to one specific person to make them laugh / impress them / help them
- Something I always look up online — could I make my own version?
- A weird, useless, beautiful thing that exists only because I made it

If you're stuck, skim [Project ideas](#project-ideas) at the bottom — but use them as starting points, not a menu. Customize. Make it about something *you* care about.

**The acid test.** Before you commit:
1. Could I describe this to my grandma in one sentence?
2. Would I actually use it / send it to one specific person?
3. Could I get a basic version working in a single sitting?

If yes to all three, build it.

**Constraints (so you don't over-scope):**
- It must run in a web browser
- It should not require user accounts/login (auth is a tar pit — skip)
- You must be able to ship a working version in three classes

### 2. Write a one-pager

New folder, open in Cursor, create `BRIEF.md`:

```markdown
# [Project Name]

## What it is (one sentence)
It's a __ that lets you __.

## Who it's for
[Yourself? A specific person? A specific kind of person? Be honest.]

## The 3 things it MUST do
1.
2.
3.

## Nice-to-haves (only if there's time)
-
-

## The vibe
[Light/dark? Playful/serious? Fast/calm? One screen or many? Mobile or desktop?]

## What "done" means
[The smallest version you'd be okay shipping if everything else fell apart.]
```

Read it out loud to a partner. They should be able to repeat back what you're building. If they can't, your brief needs work.

### 3. Get the AI's stack recommendation

In Cursor, type `@BRIEF.md` to attach your brief, then:

> Read my brief. Recommend a stack and project structure. I'm a high school student who's never built a web app — optimize for: I want every line readable to me, I want to ship in 3 class hours + an evening, I'll deploy to Vercel. What do you suggest, and why?

If it suggests something you don't recognize ("Tailwind", "Vite", "component"), ask:

> What is [thing]? Why are you suggesting it for this?

Probe before committing:

> What's the simpler alternative? Why didn't you suggest that?
> What part of this is most likely to give me trouble?

When you've decided:

> Let's go with [your choice]. Build the smallest version that does ONLY my must-haves. Make it work end-to-end before making it pretty.

Run it. Push to GitHub. Import to Vercel. **You're now on the internet.** Even if it's ugly and minimal. Knowing you'll finish is half the battle.

### 4. Build (Day 4)

| Habit | Why |
|---|---|
| Read what the AI wrote, even when it works | You can't debug code you've never seen |
| Commit small and often (every 15–20 min when something works) | You can roll back when the AI breaks something |
| Run the code every few prompts | Don't accumulate broken changes you can't see |
| Update `BRIEF.md` when plans change | If you drop a feature or pivot, write it down |
| Ask "why" when you don't understand | The AI is an expert — use it as one |

**When stuck:**

- *"It worked an hour ago and now it doesn't."* That's what commits are for. `git log --oneline` to find the last good one. `git restore .` wipes uncommitted changes back to your last commit.
- *"It kind of works but it's not what I wanted."* Be specific about the gap. Not "make it better" — describe what's wrong. *"The button works but it's tiny. Make it big, centered, contrasting color."*
- *"The AI keeps making the same mistake."* Be more specific ("Do not change the file structure. Do not add libraries.") OR start a new chat — sometimes context gets muddled.
- *"I don't know what to build next."* Open `BRIEF.md`. Which must-have is least done? Build that.
- *"I'm not understanding the code anymore."* Ask: *"Give me a one-paragraph summary of what each file is doing and how they connect."*

### 5. Polish & ship (Day 5)

By Day 5 your must-haves work. Now:

1. **Fix the one most embarrassing bug.** You know what it is.
2. **Visual polish.** *"Improve the visual design without changing features. Cohesive palette fitting [your theme], better typography, subtle hover animations."*
3. **One bit of delight.** Sound on a key click, confetti moment, hidden easter egg, mascot that reacts. *"Add [delight] when [trigger]."*
4. **Rename your Vercel URL.** Vercel dashboard → Settings → Domains → so it's `<projectname>.vercel.app` instead of random.
5. **Add a `README.md`** to your repo:

   ```markdown
   # [Project Name]

   [One-sentence description.]

   **Live demo:** https://your-project.vercel.app

   ## What it does
   [2–3 sentences. Who it's for, what it does, why you made it.]

   ## How it was built
   Built with [stack] using Cursor's AI assistance.

   ## Screenshot
   ![Screenshot](screenshot.png)
   ```

6. **Add a screenshot.** `Cmd+Shift+4` to capture, save as `screenshot.png` in your repo.
7. **Final commit and push.** Verify the live URL works.

---

## Day 6 — Demo

Each person presents in ~2 minutes:

1. **One sentence: what it is.** ("It's a __ that lets you __.")
2. **Show the live site.** Open the URL on the projector, click around, demo the main feature.
3. **One thing you're proud of.**
4. **One thing that was hard.** Be honest — where did the AI fail you? Where did *you* fail you?

While someone else is demoing, you're not on your laptop. Look up.

After demos, everyone plays everyone's apps. Open the class URL list, try at least 5 classmates' projects. When you find something cool, **tell the person directly.** "I love that you added X."

**To turn it in,** paste two things into the class showcase doc:

1. **Live URL** (`https://your-project.vercel.app`)
2. **GitHub repo URL** (`https://github.com/your-username/your-repo`)

That's submission. No file uploads, no zips, no PDFs.

---

## How you're graded

**One question:** Did you ship something that works, that you actually wanted to make?

- **"Works"** = deployed at a public URL, reachable from a phone, does what it claims.
- **"Actually wanted to make"** = not the bare-minimum example. It's a real swing at something *you* cared about — ambitious, weird, personal, funny, whatever. You'd be a little proud to text it to a friend.

Pass = both. That's the whole rubric.

If your project isn't deployed by Day 6, that's the only way to miss this — talk to me before the deadline so we fix it together.

**Don't optimize for the rubric.** Optimize for making something you'd want to remember years from now. The artifact matters more than the grade.

---

## Quick reference

### Cursor essentials

| Action | Shortcut |
|---|---|
| Open AI chat | `Cmd+L` |
| Inline edit highlighted code | `Cmd+K` |
| Open terminal | `` Cmd+` `` |
| Source Control sidebar | sidebar icon — use this, not the terminal |
| Switch model | bottom-right of chat — `Auto` is unlimited free |
| Quick open file | `Cmd+P` |
| Command palette | `Cmd+Shift+P` |

**Three rules:**
1. Read code before accepting. If unclear, ask "explain this."
2. When stuck, paste the actual error verbatim — don't paraphrase.
3. Save (`Cmd+S`) and commit every time something works.

### The deploy loop

```
Edit in Cursor → Source Control: Stage → Commit → Push
                       ↓
              Vercel auto-deploys (~30 sec)
                       ↓
                Refresh your live URL
                       ↓
                  Got an idea? GOTO 1
```

**One-time per project:** create empty GitHub repo → clone in Cursor → build → commit + push → vercel.com → New Project → Import → Deploy. From then on, every push auto-deploys.

### Prompting principles

1. **Describe outcomes, not implementations.** "Make the score pop visually" not "use a CSS keyframe with scale and color."
2. **State constraints up front.** "Plain HTML, single file, no frameworks." Otherwise it'll quietly add React.
3. **One idea per prompt.** Bundling three asks into one is a recipe for a rewrite of half your project.
4. **When stuck, paste the actual error.** Browser console → copy → into chat. Don't summarize.
5. **Ask the AI to plan before coding for big changes.** *"Don't write code yet. Walk me through what you'd build, what files would change, what could go wrong. Then I'll green-light."*
6. **If two attempts didn't fix it, change strategy.** Start a new chat or rephrase the goal.
7. **Use the AI to teach you.** *"Walk me through what you just wrote, like I'm a student who's only ever written Java."*
8. **Never paste real passwords or API keys** into a prompt.
9. **Taste is yours, not the AI's.** Cursor gives you generic-modern every time. Your colors, your jokes, your details = your project.

### The 5 git commands you actually need

```bash
git status                       # what's changed
git add .                        # stage all changes
git commit -m "what you changed" # take a snapshot
git push                         # upload to GitHub
git restore .                    # nuke uncommitted changes (rollback)
```

You can do all of this from Cursor's Source Control sidebar (no terminal needed), but knowing the commands helps when something goes weird.

### Common fixes

| Symptom | Fix |
|---|---|
| Mac won't let you install Cursor | System Settings → Privacy & Security → Allow |
| `git push` asks for password | Cursor will prompt for browser auth — let it |
| Vercel deploys but page is blank | Check capitalization — `Index.html` ≠ `index.html`. Open browser dev tools → Console for the real error |
| Push rejected ("non-fast-forward") | Source Control → ⋯ → Pull → push again |
| AI keeps wrecking working code | One change at a time. Run after each. Commit when it works. |
| Live site shows old version | Hard refresh `Cmd+Shift+R`. Vercel takes ~30 sec — wait. |
| Cursor requests run out | Switch model to `Auto` — unlimited free |
| `node_modules` showing up in git status | Ask Cursor: "add a .gitignore for a node project" |

---

## Project ideas

A starting list. **Don't pick one verbatim** — customize, twist, make it about something you care about.

### Tools that pretend to be fun
- Tip calculator where the math is presented as song lyrics
- Pomodoro timer where the break plays a clip from your favorite YouTuber
- Random restaurant picker for specifically your town
- Decision wheel — type options, spin, pick
- Habit tracker for one habit, not 50
- Countdown to your birthday / summer / a specific concert
- Tier-list maker — drag movies/songs/foods into S/A/B/C/D
- Group chooser — "who pays for dinner?" with names
- Workout shuffler with a 10-minute home routine
- Cheat sheet for a class (AP Bio terms, AP Stats formulas) but actually nice to look at

### Games
- Clicker game with upgrades, themed to your interest
- Memory match using your friends' faces, not cards
- Wordle clone with a custom word list (Pokémon names? friend nicknames?)
- Reaction-time tester (click when the screen turns green)
- Snake / Pong / Breakout heavily themed
- Idle / incremental ("grow your tomato empire")
- Speed-typing test with prompts from your favorite book
- One-button game — the whole thing is one button. What does pressing it do?
- Dice roller for D&D with animations

### Generators
- Compliment / roast / pickup-line with mood selector
- Fake startup idea generator ("Uber for X, but Y")
- D&D character / Pokémon team / aesthetic playlist title
- Magic 8-ball with custom answers
- Outfit randomizer pulling from a list you customize
- Random workout / random recipe / random study technique

### Quizzes & personality tests
- "What kind of [X] are you?" — pick any noun
- Compatibility quiz between two friends
- Niche knowledge quiz ("How well do you know [show / school inside jokes]?")
- A multi-ending choose-your-own-adventure (5 scenes, 2 endings)

### Visual / weird / artistic
- Drawing pad with color picker + save as image
- ASCII art generator — type a word, get fancy ASCII
- Color palette generator from a vibe word ("autumn," "cyberpunk")
- Mood board — drag and arrange images
- A simulator of a totally mundane thing — toast, a stoplight, a vending machine
- A page that's a love letter to a movie, a feeling, a place
- Generative art that changes each load

### Personal / portfolio
- About-me page that's weird and fun, not a résumé
- Photo gallery of one specific thing — your dog, your sneakers, sunsets
- A page for someone you love (birthday card, going-away gift, just-because)
- List of your favorite things — top 10 movies, songs, video games

### Stretch (for the ambitious)
- Anything above + a real public API (`wttr.in` weather, `dog.ceo` photos, public Pokémon API)
- Anything above as a Next.js app — Cursor scaffolds it, deploy still works
- localStorage so progress / scores persist across visits
- A page that uses your camera or microphone — fun to demo

---

## After this module

Your `vercel.app` URL stays live as long as you don't delete it. Your GitHub repo stays public. That means **for the rest of your life**, you can text someone the URL of a thing you made in high school. You can put it on a college application. You can put it on a résumé. You can show it to your kids someday and they'll be horrified by the design.

The hardest part of being a developer is starting. You started.
