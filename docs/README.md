# Bob User Guide

Welcome to Bob, your friendly and efficient chatbot! This guide will help you understand how to use Bob's features effectively.

## Launching the program

Upon launching the program, the program will output a greeting to the user, as well as an ASCII art of Bob. From there, you can input any of the available commands in the terminal.

```
_____             _ 
|  _  \           | |
| |_|  |   ____   | |
|     /   / __ \  | |___
|  _  \  | |  | | |  __ \
| |_|  | | |__| | | |__| |
|_____/   \____/  |_____/

__________________________________________________________________________________________________________________
Hello! I'm Bob!
What can I do for you?
__________________________________________________________________________________________________________________
Enter input: 
```



## Features

### 1. List
The `list` feature allows you to view all your saved tasks. This is useful for keeping track of ongoing activities or pending actions.

#### How to Use:
- Simply type `list` in the chat.
- Bob will respond with all recorded tasks.
- Example:
  ```
  User: list
  Bob: Here are the tasks in your list:
  1. [D][X] finish UG (by: Friday, 2359)
  2. [E][X] CS2113 Lecture (from: 1600 to: 1800)
  3. [T][X] do ip


### 2. Mark/Unmark
The `mark` and `unmark` feature allows you to mark and unmark tasks. This is useful for keeping track of completed tasks.

#### How to Use:
- Format: `mark <task number>`
- Example:
  ```
  User: mark 1
  Bob: Nice! I've marked this task as done:
  [D][X] finish UG (by: Friday, 2359)

- Format: `unmark <task number>`
- Example:
  ```
  User: unmark 1
  Bob: OK! I've marked this task as not done yet:
  [D][X] finish UG (by: Friday, 2359)

### 3. Todo
The `todo` feature adds and saves a 'Todo' task into the list. This is useful for pending tasks that do not really have a deadline.

#### How to Use:
- Format: `todo <task name>`
- Example:
  ```
  User: todo do ip
  Bob: Got it. I've added this task:
  [T][ ] do ip
  You now have 8 tasks in the list.

### 4. Deadline
The `deadline` feature adds and saves a 'Deadline' task into the list. This is useful for pending tasks that have a deadline.

#### How to Use:
- Format: `deadline <task name> /by <deadline>`
- Example:
  ```
  User: deadline finish UG /by: Friday, 2359
  Bob: Got it. I've added this task:
  [D][ ] finish UG (by: Friday, 2359)
  You now have 9 tasks in the list.

### 5. Event
The `event` feature adds and saves an 'Event' task into the list. This is useful for recording events with a specified duration.

#### How to Use:
- Format: `event <task name> /from <start time> /to <end time>`
- Example:
  ```
  User: event CS2113 lecture /from 1600 /to 1800
  Bob: Got it. I've added this task:
  [E][ ] CS2113 lecture (from: 1600 to: 1800)
  You now have 10 tasks in the list.

### 6. Delete
The `delete` feature deletes a task from the list.

#### How to Use:
- Format: `delete <task number>`
- Example:
  ```
  User: delete 1
  Bob: Noted. I've removed this task:
  [E][ ] CS2113 lecture (from: 1600 to: 1800)
  You now have 9 tasks in the list.

### 7. Find
The `find` feature finds all tasks from the list based on a given keyword.

#### How to Use:
- Format: `find <keyword>`
- Example: 
  ```
  User: find ip
  Bob: Here are the matching tasks in your list:
  1. [T][ ] do ip


### 8. Exit Program
The `bye` command allows the user to exit the program.

#### How to Use:
- Simply type `bye` in the chat.
- Example:
  ```
  User: bye
  Bob: Bye. Hope to see you again soon!
  