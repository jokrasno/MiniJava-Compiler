# MiniJava-to-MIPS Compiler

A complete compiler for the MiniJava language (a pedagogical subset of Java) that produces MIPS assembly output. Built as the culmination of a semester-long Compiler Design course, combining five incremental assignments into a single, clean, buildable project.

---

## Architecture Overview

```
  ┌─────────────────────────────────────────────────────────────────────┐
  │                    MiniJava Source (.java)                          │
  └──────────────────────────┬──────────────────────────────────────────┘
                             │
                    ┌────────▼────────┐
                    │  Lexer/Scanner  │  Assignment 1 — TokenGrammar
                    │  (wrangLR)      │  Tokenization, whitespace, comments,
                    │                 │  string/char/integer literals, keywords
                    └────────┬────────┘
                             │ token stream
                    ┌────────▼────────┐
                    │     Parser      │  Assignment 2 — MJGrammar
                    │  (wrangLR LR)   │  Recursive-descent grammar → AST
                    │                 │  Produces full syntax tree
                    └────────┬────────┘
                             │ AST
              ┌──────────────▼──────────────┐
              │    Semantic Analysis         │  Assignments 3–4
              │                              │
              │  Sem1: Declaration checking  │  Duplicate classes/fields/methods
              │  Sem2: Name resolution       │  Scope, field/method lookup, inheritance
              │  Sem3: Initialization check  │  Uninitialized variable detection
              │  Sem4: Type checking         │  Subtyping, casts, instanceof, assignments
              │  Sem5: Constant evaluation   │  Constant folding, unreachable code warnings
              └──────────────┬───────────────┘
                             │ annotated AST
              ┌──────────────▼───────────────┐
              │     Code Generation           │  Assignment 5
              │                               │
              │  CG1: Vtable + offset layout  │  Field/method offsets, class descriptors
              │  CG2: String literal emission │  .data section for string constants
              │  CG3: MIPS instruction emit   │  .text section with full MIPS assembly
              └──────────────┬────────────────┘
                             │
                    ┌────────▼────────┐
                    │  MIPS Assembly  │  .asm output
                    │  (.asm file)    │  Runnable via RIPS or SPIM
                    └─────────────────┘
```

### What I implemented vs. provided infrastructure

| Component | Author | Files |
|---|---|---|
| Lexer (token grammar) | Student | `parse/original/TokenGrammar.java` |
| Parser (grammar rules + AST actions) | Student | `parse/MJGrammar.java` |
| Semantic visitors (Sem1–Sem5) | Student | `visitor/Sem{1–5}Visitor.java` |
| Code generation (CG1–CG3) | Student | `visitor/CG{1–3}Visitor.java` |
| AST node classes | Instructor | `syntaxtree/*.java` |
| Error/message handling | Instructor | `errorMsg/*.java` |
| Visitor interfaces | Instructor | `visitor/Visitor.java`, `Visitor2.java` |
| Parse table generator | Tool | `wrangLR.jar` |
| Helper visitors | Instructor | `ASTGenVisitor`, `PrettyPrintVisitor`, `TreeDrawerVisitor`, etc. |

---

## Usage Walkthrough

### Prerequisites

- **Java 21** (JDK)
- **make** (optional — manual commands provided below)
- A MIPS simulator for running output (eg SPIM)

### Clone and Build

```bash
git clone <repo-url>
cd COMPILER/src
make all
```

Or manually:

```bash
cd COMPILER/src
javac -cp ".;wrangLR.jar" -source 21 -target 21 errorMsg/*.java syntaxtree/*.java visitor/*.java main/*.java parse/MJGrammar.java parse/MJGrammarParseTable.java Lib.java
```

### Compile a MiniJava Program

```bash
# Default: outputs to same-name .asm file
make run FILE=../tests/FibIterative.java

# With explicit output file
java -cp ".;wrangLR.jar" main.Main ../tests/FibIterative.java -o output.asm

# Print to console instead of file
java -cp ".;wrangLR.jar" main.Main ../tests/FibIterative.java -c

# Append the MIPS runtime library (needed for standalone execution)
java -cp ".;wrangLR.jar" main.Main ../tests/FibIterative.java -a mjLib-rust.asm
```

### Run the Output

Using SPIM:
```bash
spim -file output.asm
```

### Command-Line Options

| Flag | Description |
|------|-------------|
| *(input file)* | Required — the MiniJava `.java` source file |
| `-o <file>` | Write MIPS output to specified file |
| `-c` | Write MIPS output to console |
| `-a <file>` | Append a file (e.g., `mjLib-rust.asm`) to the output |
| `-p` | Print AST in tree form |
| `-pp` | Pretty-print the AST |
| `-vpp` | Verbose pretty-print with types |

### Run the Test Suite

```bash
make test
```

---

## Language Features

### Types

- **Primitives**: `int`, `boolean`, `char`
- **Reference**: `String`, arrays, user-defined classes
- **Special**: `null`, `void`

### Object-Oriented Features

- Classes with fields and methods
- Single inheritance via `extends`
- Method overriding (with return-type checking)
- `this` and `super` references
- `instanceof` type tests
- Type casts with runtime safety
- Predefined classes: `Object`, `String`, `Lib` (I/O)

### Control Flow

- `if` / `else`
- `while` loops
- `for` loops
- `do-while` loops
- `switch` / `case` / `default`
- `break` (with proper scoping)

### Arrays

- Array creation: `new int[10]`
- Array access: `arr[i]`
- Array length: `arr.length`
- Multi-dimensional arrays

### Expressions

- Binary operators: `+`, `-`, `*`, `/`, `%`, `&&`, `||`, `==`, `!=`, `<`, `>`, `<=`, `>=`
- Unary operators: `!`, `-`
- Method calls, field access, array lookup
- Integer, string, and character literals
- `null` literal

### Semantic Analysis

- Duplicate declaration detection (classes, fields, methods, variables)
- Scope resolution (local variables, fields, inherited members)
- Uninitialized variable detection
- Full type checking with subtyping and inheritance
- Cast validation (upcasts, downcasts, cross-hierarchy detection)
- `instanceof` validation
- Constant expression evaluation
- Unreachable code warnings

---


## Project Structure

```
COMPILER/
├── src/
│   ├── parse/              # Lexer & parser
│   │   ├── MJGrammar.java           (student — parser grammar)
│   │   ├── MJGrammarParseTable.java (generated — wrangLR output)
│   │   ├── MJScanner.class          (precompiled — enhanced scanner)
│   │   ├── original/TokenGrammar.java  (student's original lexer)
│   │   └── ...
│   ├── visitor/             # All compiler passes
│   │   ├── Sem1Visitor.java ... Sem5Visitor.java  (student — semantics)
│   │   ├── CG1Visitor.java ... CG3Visitor.java    (student — code gen)
│   │   ├── CodeStream.java          (instructor — MIPS output helper)
│   │   ├── VtableGenerator.class    (precompiled — vtable layout)
│   │   └── ...
│   ├── syntaxtree/          # AST node classes (instructor)
│   ├── errorMsg/            # Error reporting (instructor)
│   ├── main/                # Entry point (instructor)
│   ├── Lib.java             # Predefined I/O library class
│   ├── wrangLR.jar          # Parser generator tool
│   ├── mjLib-rust.asm       # MIPS runtime library
│   └── Makefile
├── runtime/
│   └── RunMain.java         # MIPS runtime entry point
├── tests/                   # Test programs
│   ├── *.java               # Full programs (Fibonacci, Heapsort, etc.)
│   ├── A/                   # Basic feature tests
│   ├── 5a/, 5b/             # Code generation test batches
│   ├── semantic/            # Type error test cases
│   └── lexer/               # Lexer edge case tests
├── CHANGES.md               # Modifications from original assignments
└── README.md
```
