# Regular Expressions: Competency List

The **Basics** are expected of every student. **Advanced** competencies are optional extensions for students who want to explore the topic further. Each competency emphasizes concepts that apply across programming languages, with Java-specific implementation goals where appropriate.

## Learning plan (9 × 50 minutes)

Each lesson includes a short concept input and substantial time for guided and independent practice. The advanced route is for students who progress quickly: they should first secure the basic outcomes, then use the extension tasks to work towards the advanced outcomes. Advanced work is included throughout the sequence, not reserved only for students who finish early at the end.

| Lesson | Focus and outcomes | Suggested use of 50 minutes | Advanced route |
| --- | --- | --- | --- |
| 1 | What regexes are for; literals, metacharacters, escaping, and Java string escaping. | 5 min entry task; 12 min concept and examples; 25 min write and test simple search patterns; 8 min check and reflection. | Explain where regexes are and are not suitable; experiment with `Pattern.quote()`. |
| 2 | Character classes, ranges, negation, predefined classes, wildcard, and quantifiers. | 5 min retrieval; 12 min worked examples; 25 min describe and build patterns for text constraints; 8 min check and reflection. | Explore Unicode properties such as `\p{L}` and compare them with shorthand classes. |
| 3 | Anchors, word boundaries, whole-input versus substring matching; Java `matches()`, `find()`, and `lookingAt()`. | 5 min retrieval; 12 min demonstrations; 25 min classify and implement matching tasks; 8 min check and reflection. | Compare how anchors and flags affect multiline input. |
| 4 | Alternation and choosing between pattern alternatives. | 5 min retrieval; 12 min worked examples; 25 min build and test patterns with alternatives; 8 min check and reflection. | Explore grouping to control repetition and capture parts of matches. |
| 5 | Java `Pattern` and `Matcher`; finding multiple matches, `String` regex methods, and invalid-pattern handling. | 5 min retrieval; 12 min API demonstration; 25 min implement small search and validation tasks; 8 min check and reflection. | Use groups to extract structured fields; try `Matcher.results()` or `Pattern.asPredicate()`. |
| 6 | Replacements and systematic test cases. | 5 min retrieval; 12 min examples; 25 min transform text and test a Java regex-based method; 8 min check and reflection. | Use captured-group references in replacements; explore regex flags and lookaround. |
| 7 | Consolidation of all basic outcomes through a multi-step text-processing task. | 5 min retrieval; 8 min task briefing; 29 min differentiated practice; 8 min review and reflection. | Extend the task with groups, lookaround, Unicode properties, and named groups; explain its limits. |
| 8 | Advanced patterns: lookaround, Unicode, regex flavors, backtracking, performance, and regex options. | 5 min retrieval; 12 min demonstrations; 25 min investigate and improve patterns; 8 min check and reflection. | Compare Java regex behavior with another flavor; investigate catastrophic backtracking and test possessive quantifiers or atomic groups safely. |
| 9 | Apply and reflect: combine regex skills in a small tokenizer or structured-text task. | 5 min retrieval; 8 min plan; 29 min implement and test; 8 min present or review. | Use Java stream or functional-replacement APIs and extend the tokenizer with additional token types. |

The 25–29 minute practice periods should include a core task that checks the basic outcomes and optional extension tasks for students ready to go further. The final task is an opportunity to demonstrate learning, not a prerequisite for completing the basic competencies.

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
- **Java:** Escape regex metacharacters through Java string literals correctly; use `Pattern.quote()` when treating arbitrary text as a literal pattern.

### 3. Describe repetition
- Use `*`, `+`, `?`, and `{n}`, `{n,}`, `{n,m}` to specify how many times an element may occur.
- Explain the difference between greedy and reluctant quantifiers and predict their matches on example input.

### 4. Restrict where a match can occur
- Use anchors such as `^` and `$`, and word boundaries such as `\b`, to express position requirements.
- Distinguish a match of the entire input from a match of a substring.
- **Java:** Select the appropriate operation: `matches()` for the entire input, `find()` for a match anywhere, and `lookingAt()` for a match at the beginning.

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
- Use `Matcher.find()` repeatedly to process multiple matches, and use `start()` and `end()` to locate them.
- Use the convenient `String` methods `matches()`, `split()`, `replaceAll()`, and `replaceFirst()` where suitable.
- Recognize and handle invalid patterns, for example by catching `PatternSyntaxException`.

### 8. Replace matched text
- Replace matched text with literal replacement text.
- **Java:** Use `Matcher.replaceAll()` or `String.replaceAll()`; use `Matcher.quoteReplacement()` when replacement text should be treated literally.

## Advanced (optional)

### 1. Use groups to structure and capture patterns
- Use groups (`(...)`) to treat parts of a pattern as a unit, including applying quantifiers to alternatives or sub-patterns.
- Distinguish capturing groups from non-capturing groups (`(?:...)`).
- Retrieve captured parts of a match and use numbered backreferences where appropriate.
- **Java:** Access the complete match and captured groups with `Matcher.group()`, `group(int)`, and `groupCount()`.
- Use captured-group references in replacement text.
- **Java:** Use `$1` or `${name}` in replacement strings.

### 2. Use named groups and backreferences
- Use named capturing groups and backreferences to identify or refer to repeated text, such as detecting repeated words.
- **Java:** Define named groups with `(?<name>...)`, retrieve them with `Matcher.group("name")`, and refer to them in a pattern with `\k<name>`.

### 3. Use lookaround assertions
- Explain that lookahead and lookbehind assertions check surrounding text without consuming it.
- Use positive and negative lookahead or lookbehind to express context-dependent constraints.
- **Java:** Use `(?=...)`, `(?!...)`, `(?<=...)`, and `(?<!...)` with awareness of Java's lookbehind requirements.

### 4. Reason about backtracking and performance
- Explain at a high level how a backtracking regex engine tries alternatives and repetitions.
- Recognize patterns that can cause excessive backtracking and describe the risk of R on untrusted input.
- Improve a problematic pattern and compare its behavior and performance on relevant inputs.
- **Java:** Use possessive quantifiers or atomic groups where appropriate, and measure regex performance with controlled inputs.

### 5. Work with Unicode and regex flavors
- Distinguish bytes, Unicode code points, and user-perceived characters.
- Use Unicode properties to match character categories and explain that shorthand classes may vary between regex flavors or options.
- Compare features or behavior across regex implementations, including Java.
- **Java:** Use Unicode property expressions such as `\p{L}` and `\p{IsGreek}`, and understand the effect of `Pattern.UNICODE_CHARACTER_CLASS`.

### 6. Use advanced Java regex APIs
- Choose higher-level APIs when processing sequences of matches or splitting text lazily.
- **Java:** Use APIs such as `Matcher.results()`, `Pattern.splitAsStream()`, `Pattern.asPredicate()`, or functional replacement where appropriate.

### 7. Build a simple tokenizer
- Combine multiple patterns to identify token types in structured text.
- **Java:** Implement a simple tokenizer with `Pattern` and `Matcher`, and report or handle input that does not match any token.

### 8. Use common regex options
- Explain how case-insensitive, multiline, and dot-matches-line-terminators options affect matching.
- **Java:** Apply options such as `Pattern.CASE_INSENSITIVE`, `Pattern.MULTILINE`, and `Pattern.DOTALL`, or their inline equivalents.
