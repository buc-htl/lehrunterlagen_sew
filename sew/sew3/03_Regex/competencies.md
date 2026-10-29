# Regular Expressions: Competency List

The **Basics** are expected of every student. **Advanced** competencies are optional extensions for students who want to explore the topic further. Each competency emphasizes concepts that apply across programming languages, with Java-specific implementation goals where appropriate.

## Basics

### 1. Understand what regular expressions are for
- Explain what a regular expression (regex) describes and how it can be used to search, validate, extract, split, and replace text.
- Distinguish a regex pattern from the input text and from the resulting match.
- Recognize cases where a regex is not an appropriate tool, such as parsing arbitrarily nested structures.

### 2. Recognize and use basic pattern syntax
- Match literal characters and explain the role of common metacharacters.
- Escape metacharacters when they should be matched literally.
- Use character classes, ranges, negated character classes, and common predefined classes such as digits, whitespace, and word characters.
- Use the wildcard `.` and describe its behavior, including that line terminators are normally excluded.
- Understand the effect of `Pattern.UNICODE_CHARACTER_CLASS`.

### 3. Describe repetition
- Use `*`, `+`, `?`, and `{n}`, `{n,}`, `{n,m}` to specify how many times an element may occur.
- Explain the difference between greedy and reluctant quantifiers and predict their matches on example input.

### 4. Restrict where a match can occur
- Use anchors such as `^` and `$`, and word boundaries such as `\b`, to express position requirements.
- Distinguish a match of the entire input from a match of a substring.

### 5. Combine alternatives
- Use alternation (`|`) to express a choice between patterns.
- Place alternatives carefully so the pattern accepts the intended options and not unintended input.

### 6. Read, design, and test patterns
- Explain in plain language what a given regex matches.
- Translate a clear text-matching requirement into a regex and refine it with representative examples.
- Include both expected matches and non-matches when testing a pattern, including boundary cases.
- Use a regex testing tool or debugger to inspect why a pattern matches or fails.

### 7. Apply regular expressions in Java
- Compile a pattern with `Pattern.compile()` and create a `Matcher` for input text.
- Use `Matcher.matches()`, `Matcher.lookingAt()` to check for matches.
- Use `Matcher.find()` repeatedly to process multiple matches, use Matcher.group() to retrieve the matched text, and use `start()` and `end()` to locate them.
- Use the convenient `String` methods `matches()`, `split()`, `replaceAll()`, and `replaceFirst()` where suitable.
- Recognize and handle invalid patterns, for example by catching `PatternSyntaxException`.

### 8. Use common regex options
- Explain how case-insensitive, multiline, and dot-matches-line-terminators options affect matching.
- **Java:** Apply options such as `Pattern.CASE_INSENSITIVE`, `Pattern.MULTILINE`, and `Pattern.DOTALL`

## Advanced

### 1. Use groups to structure, capture, and reuse patterns
- Use groups (`(...)`) to treat parts of a pattern as a unit, including applying quantifiers to alternatives or sub-patterns.
- Distinguish capturing groups from non-capturing groups (`(?:...)`).
- Retrieve captured parts of a match and use numbered backreferences where appropriate.
- **Java:** Access the complete match and captured groups with `Matcher.group()`, `group(int)`, and `groupCount()`.
- Use captured-group references in replacement text.
- **Java:** Use `$1` or `${name}` in replacement strings.
- Use named capturing groups and backreferences to identify or refer to repeated text, such as detecting repeated words.
- **Java:** Define named groups with `(?<name>...)`, retrieve them with `Matcher.group("name")`, and refer to them in a pattern with `\k<name>`.

### 2. Use lookaround assertions
- Explain that lookahead and lookbehind assertions check surrounding text without consuming it.
- Use positive and negative lookahead or lookbehind to express context-dependent constraints.
- **Java:** Use `(?=...)`, `(?!...)`, `(?<=...)`, and `(?<!...)` with awareness of Java's lookbehind requirements.

### 3. Reason about backtracking and performance
- Explain at a high level how a backtracking regex engine tries alternatives and repetitions.
- Recognize patterns that can cause excessive backtracking and describe the risk of R on untrusted input.
- Improve a problematic pattern and compare its behavior and performance on relevant inputs.


### 4. Work with Unicode and regex flavors
- Distinguish bytes, Unicode code points, and user-perceived characters.
- Use Unicode properties to match character categories and explain that shorthand classes may vary between regex flavors or options.
- Compare features or behavior across regex implementations, including Java.
- **Java:** Use Unicode property expressions such as `\p{L}` and `\p{N}`


### 5. Build a simple tokenizer
- Combine multiple patterns to identify token types in structured text.
- **Java:** Implement a simple tokenizer with `Pattern` and `Matcher`, and report or handle input that does not match any token.

