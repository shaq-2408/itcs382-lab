# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/shaq-2408/itcs382-lab

## Tools

| Item | Version or name |
| --- | --- |
| JDK | 25.0.4.1 (Temurin OpenJDK) |
| Maven | 3.9.11 |
| IDE | Visual Studio Code |
| SonarQube for IDE | SonarQube for IDE (SonarSource) |
| AI tool and exact model | ChatGPT — GPT-6 |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change:
a6d5f66

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | java:S2143 | ParcelService.java:1 | Use the java.time API for date and time |
| 2 | java:S1128 | ParcelService.java:6 | Remove this unused import 'java.util.Date' |
| 3 | java:S1068 | ParcelService.java:12 | Remove this unused private field |
| 4 | java:S106 | ParcelService.java:30 | Replace this use of System.out by a logger |
| 5 | java:S4973 | ParcelService.java:42 | Strings and Boxed types should be compared using equals() |
| 6 | java:S106 | ParcelService.java:67 | Replace this use of System.out by a logger |
| 7 | java:S106 | ParcelService.java:69 | Replace this use of System.out by a logger |
| 8 | java:S2095 | ParcelService.java:76 | Use try-with-resources or close this FileWriter in a finally clause |
| 9 | java:S108 | ParcelService.java:80 | Remove this block of code, fill it in, or add a comment explaining why it is empty |
| 10 | java:S106 | Main.java:9 | Replace this use of System.out by a logger |
| 11 | java:S106 | Main.java:15 | Replace this use of System.out by a logger |
| 12 | java:S106 | Main.java:17 | Replace this use of System.out by a logger |
| 13 | java:S106 | Main.java:22 | Replace this use of System.out by a logger |

## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | `25 October 2026` |
| AI tool and exact model | `ChatGPT — GPT-6` |
| Commit reviewed | `a6d5f66` |
| How the AI read the files | Shared code screenshots in the chat |
| Output file in the reviews folder | |

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
| 1 | Main.java:8 | Scanner is not closed | AI | Note | Use try-with-resources. |
| 2 | Main.java:11 | Command arguments are accessed without checking their number | AI | Should fix | Validate command arguments before accessing the array. |
| 3 | Parcel.java:7 | The status field is public | AI | Should fix | Make the field private and provide access methods. |
| 4 | ParcelService.java:6 | Unused import java.util.Date | Both | Note | Remove the unused import. |
| 5 | ParcelService.java:12 | Unused maxParcels field | Both | Note | Remove the field or use it. |
| 6 | ParcelService.java:22 | getLockers() exposes the internal mutable collection | AI | Should fix | Return a defensive copy or unmodifiable view. |
| 7 | ParcelService.java:39 | String comparison uses == instead of equals() | Both | Must fix | Use equals() to compare strings. |
| 8 | ParcelService.java:76 | FileWriter is not closed | Both | Must fix | Use try-with-resources. |
| 9 | ParcelService.java:80 | Empty catch block | Both | Should fix | Handle or log the IOException. |
| 10 | ParcelService.java:65 | FileWriter overwrites parcels.csv | AI | Should fix | Use append mode if existing records must be preserved. |
| 11 | ParcelService.java:31 | storeParcel() has several responsibilities | AI | Should fix | Separate persistence and notification responsibilities. |
| 12 | Main.java:8 | Main handles input, command parsing and service coordination | AI | Note | Extract command parsing into a separate method. |

2.1 Name two problems that only the AI review found.
The AI review found that Main does not validate the number of command arguments before accessing them. It also found that getLockers() exposes the internal mutable collection.

2.2 Name one problem that only SonarQube found, or write none.
SonarQube found the unused private field `maxParcels` in `ParcelService.java`. The AI review also identified this issue, so there is no confirmed problem found only by SonarQube. Answer: none.

2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.
The AI finding about `FileWriter` overwriting `parcels.csv` is a valid concern if previous records must be preserved. I checked the code and confirmed that `new FileWriter("parcels.csv")` opens the file in overwrite mode.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | | |

Number of SonarQube issues that remain after your fixes:

The two design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| | | |
| | | |

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text

```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | | |
| AI tool and exact model | | |
| Prompt (B or C as given, or your changed prompt in full) | | |
| Builds with mvn package (yes or no) | | |
| Number of classes, interfaces, enums and records | | |
| SonarQube issues, total | | |
| SonarQube issues, by rule | | |
| AI review findings in Check 1 | | |
| AI review findings in Check 2 | | |
| Review output file in the reviews folder | | |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.

4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.

4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.

5.2 When should you not trust the AI review? Give an example from this lab.

5.3 What will you do differently in Project Phase 2 after this lab?

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| | | | |
