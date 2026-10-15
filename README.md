# Coffee Shop Resource Simulation — Java

An object-oriented coursework prototype for modelling resource production, conversion and consumption. The source contains a general simulation engine and a cafe-themed extension with six entities: bean grinder, water pump, milk supplier, espresso machine, latte machine and customer counter.

## Design

- Producer, converter and consumer abstractions with a shared simulation context.
- Resource inventories, tick processing and history snapshots.
- A command parser and separate command classes for tick, help, quit, info, graph, save, load, build and cheat operations.
- Cafe entities model the sequence from ingredients to drinks and served customers.

## Run the cafe demonstration

Requires a JDK; verified locally with JDK 25.

```sh
sh demo.sh
```

The portfolio demonstration calls the original cafe entities in production order for five ticks and prints the remaining beans and served customers. `examples/CafeDemo.java` is a small demonstration added when preparing this portfolio; the application Java files under `src/` retain the university GitLab version.

## Tests

```sh
sh test.sh
```

This runs the original coursework JUnit suite. On the first run, the script downloads the pinned JUnit console dependency from Maven Central using `curl`; subsequent runs reuse the local copy. An internet connection is required for the first download. The original JUnit suite reports 30 of 31 tests passing. The remaining save test expects `data/engine_save_test.csv`, while the implementation writes the supplied relative filename in the working directory. The original tests also print zero marks for three unsupported command aliases. These are coursework checks, not comprehensive coverage of all behaviours.

## Scope and limitations of the saved version

The original `Main.java` is empty, so the original `run.sh` does not start an interactive application. Use `demo.sh` for the cafe demonstration. The parser/engine commands operate on the general resource model; the cafe extension is separate. Save writes general resources and entity descriptions, while load restores general resources and resets the tick count; it does not reconstruct a full cafe simulation. Short command aliases are not implemented. Some converter operations can consume one ingredient when another is unavailable, which would need improvement for a robust simulation.

## Structure and provenance

- `src/org/uob/a2/engine/`: generic engine and state.
- `src/org/uob/a2/model/`: resource types and cafe entities.
- `src/org/uob/a2/parser/`: commands and parser.
- `test/`: original coursework tests. The test script downloads its JUnit dependency to `lib/`.
- `examples/`, `demo.sh`: portfolio demonstration.

Portfolio copy of my University of Birmingham Object Oriented Programming coursework, snapshot `3c6151f97ae6a980412e46a1e6e22d5730c1190e`. README, demonstration, ignore rules and test wrapper were added for this portfolio. Coursework scaffolding and supplied tests are included as context, not claimed as wholly original work. Compiled classes and notebook checkpoints are excluded.
