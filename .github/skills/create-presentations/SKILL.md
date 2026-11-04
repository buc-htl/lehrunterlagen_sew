---
name: create-presentations
description: "Create or revise consistent Reveal.js slide decks for software development lessons in this repository. Use when asked for a presentation, slides, lesson deck, or Reveal.js material about programming, Java, algorithms, testing, regex, recursion, or related software-engineering topics."
argument-hint: "Topic, audience, scope, and any source material"
user-invocable: true
---

# Reveal.js Presentations for Software Development

Create clear, consistent Reveal.js presentations that fit the repository's software-development teaching materials.

## When to Use

- Create a new presentation or revise an existing slide deck.
- Turn lesson notes, competencies, or source material into presentation slides.
- Improve the teaching flow, examples, or visual consistency of a Reveal.js deck.

## Procedure

1. Establish the topic, audience, prior knowledge, scope, and requested language from the request and nearby lesson materials. If not specified, target HTL SEW students and use German, matching the surrounding course materials. Ask only when missing information would materially change the deck.
2. Inspect the destination chapter and a nearby working presentation. Use [the advanced Regex deck](../../../sew/sew3/03_Regex/regex_adv.html) as a structural example and reuse the shared [HTL theme](../../../theme/htl.css). Check the repository's `sew/reveal-config.js` and the nearest deck's Reveal.js setup before writing or changing runtime markup.
3. Build a coherent learning sequence suited to the scope: introduce the problem and learning outcomes, develop concepts in small steps, show worked examples in the course language, give learners a chance to predict or practise, address common mistakes, and close with a concise recap or check for understanding. Adapt the sequence to the topic instead of adding empty or repetitive slides.
4. Create or edit the deck in the relevant chapter folder. Follow the established HTML structure (`.reveal` / `.slides`, with one `<section>` per slide), use a descriptive title and correct document language, and keep the shared theme. Reuse the local Reveal.js assets and configuration pattern from a nearby deck; calculate all relative paths from the destination file. Do not add a dependency or custom styling when the existing setup can do the job.
5. Keep each slide focused on one main point. Prefer short explanations, meaningful headings, readable code examples, and diagrams or tables only when they make the idea easier to understand. Label code language and show expected output or a trace where useful. Ensure examples match the stated language/runtime and do not invent unsupported behavior.
6. Verify the result: check the slide order and HTML structure, confirm relative asset and configuration paths resolve from the new file, and review code examples and terminology for consistency with the lesson and its prerequisites. Make the smallest relevant correction if any check fails.

## Conventions

- Match the wording, level, and language of the surrounding chapter unless the user requests otherwise.
- Preserve existing course content and links when revising a deck; do not replace them with generic material.
- Prefer the repository's shared styles and conventions over one-off CSS.
- Keep slide content presentation-friendly; put extended explanations in the lesson notes or source materials rather than overcrowding a slide.
