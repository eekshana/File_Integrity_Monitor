# File Integrity Monitor

**Student Name:** Pola Eekashan  
**Registration Number:** 24BCY10024  

## About the Project

The File Integrity Monitor is a small Java command-line project that checks whether files in a folder have changed.

The program first creates a baseline of the files in a directory. It calculates a SHA-256 hash for each file and saves the information locally. When the user checks the folder again later, the program compares the new scan with the saved baseline.

This makes it possible to see whether a file was added, changed, or deleted.

The project was made as a practical Java programming exercise and uses separate classes for the different parts of the application.

## What the Program Can Do

- Scan a directory and its subdirectories
- Calculate SHA-256 hashes for files
- Create and save a baseline
- Load an existing baseline
- Find newly added files
- Find modified files
- Find deleted files
- Show the saved baseline
- Display a simple integrity report
- Handle common invalid inputs
- Run completely from the command line

## Technologies Used

- Java
- Object-oriented programming
- `ArrayList`
- `HashMap`
- File input/output
- Exception handling
- SHA-256 hashing
- Command-line interface

No external libraries are needed.

## Project Structure

```text
FileIntegrityMonitor/
│
├── README.md
├── statement.md
│
├── src/
│   ├── Main.java
│   ├── FileScanner.java
│   ├── HashGenerator.java
│   ├── FileRecord.java
│   ├── IntegrityChecker.java
│   ├── ReportGenerator.java
│   ├── StorageManager.java
│   └── InputValidator.java
│
├── data/
│
├── docs/
│
└── test-cases.md
```

## What Each Java File Does

### Main.java

This is the starting point of the program. It displays the menu and connects the different parts of the application.

### FileScanner.java

This class searches through the selected directory and its subdirectories and collects information about the files it finds.

### HashGenerator.java

This class calculates the SHA-256 hash of a file. The hash is used when comparing the current file with the baseline.

### FileRecord.java

This class represents one file and stores its path, hash, size, and last modified time.

### IntegrityChecker.java

This class compares the saved baseline with the latest scan and works out which files were added, modified, or deleted.

### ReportGenerator.java

This class prints the results of an integrity check in a readable format.

### StorageManager.java

This class saves the baseline to `data/baseline.txt` and loads it when the program needs it.

### InputValidator.java

This class handles basic input checking so that incorrect menu entries and empty paths do not immediately stop the program.

## Requirements

You need:

- Java JDK 8 or newer
- A terminal
- VS Code or another Java editor

There are no third-party dependencies.

## Check Java Before Running

Open a terminal and run:

```bash
java -version
```

Then:

```bash
javac -version
```

If both commands show a Java version, you can continue.

## Running the Project

Open the terminal in the `FileIntegrityMonitor` project folder.

For example:

```bash
cd /path/to/FileIntegrityMonitor
```

Compile all the Java files:

```bash
javac src/*.java
```

If compilation finishes without errors, start the program with:

```bash
java -cp src Main
```

### Important

Only `Main.java` is the entry point of the application.

Do not try to run files such as `HashGenerator.java`, `InputValidator.java`, or `FileScanner.java` separately. They are supporting classes used by `Main.java`.

## Using the Program

After starting the program, the following menu appears:

```text
========================================
       FILE INTEGRITY MONITOR
========================================

----------------------------------------
1. Create / Update Baseline
2. Check File Integrity
3. Show Baseline Files
4. Exit
----------------------------------------
Enter your choice:
```

### 1. Create / Update Baseline

Choose:

```text
1
```

The program will ask for the directory you want to check.

For example:

```text
Enter directory path: test-folder
```

The program scans the folder and calculates a SHA-256 hash for each file.

The baseline is then saved here:

```text
data/baseline.txt
```

### 2. Check File Integrity

After creating a baseline, choose:

```text
2
```

Enter the same directory path.

The program scans the directory again and compares the results with the baseline.

It reports three types of changes:

- Added files
- Modified files
- Deleted files

For example:

```text
========================================
        FILE INTEGRITY REPORT
========================================
Added files    : 1
Modified files : 1
Deleted files  : 0
========================================
```

### 3. Show Baseline Files

Choose:

```text
3
```

This displays the files currently stored in the baseline and their SHA-256 hashes.

### 4. Exit

Choose:

```text
4
```

The program closes.

## A Simple Way to Test It

Create a small folder for testing, for example:

```text
test-folder/
├── file1.txt
├── file2.txt
└── file3.txt
```

Run the program and create a baseline for this folder.

After that, try the following:

### Test 1: Leave Everything Alone

Run an integrity check without changing anything.

The program should show zero added, modified, and deleted files.

### Test 2: Change a File

Open `file1.txt`, change its contents, save it, and run the integrity check again.

The file should be listed as modified.

### Test 3: Add a File

Create a new file called `file4.txt` and run another integrity check.

The new file should be listed as added.

### Test 4: Delete a File

Delete `file3.txt` and run another integrity check.

The deleted file should be listed as deleted.

## Example Output

A normal baseline creation may look like this:

```text
========================================
       FILE INTEGRITY MONITOR
========================================

----------------------------------------
1. Create / Update Baseline
2. Check File Integrity
3. Show Baseline Files
4. Exit
----------------------------------------
Enter your choice: 1

Enter directory path: test-folder

Scanning directory...
Files found: 3
Baseline saved successfully.
Location: data/baseline.txt
```

After changing a file, the integrity check may show:

```text
========================================
        FILE INTEGRITY REPORT
========================================
Added files    : 0
Modified files : 1
Deleted files  : 0
========================================

----- MODIFIED FILES -----

/path/to/test-folder/file1.txt
SHA-256: [calculated hash]
```

## How the Comparison Works

The program uses the file path to match files between the old and new scans.

If a path appears in the new scan but not in the baseline, the file is treated as added.

If a path appears in both scans but the SHA-256 values are different, the file is treated as modified.

If a path appears in the baseline but not in the new scan, the file is treated as deleted.

## Where Data Is Stored

The application keeps its baseline in:

```text
data/baseline.txt
```

The `data` folder can be empty when the project is first downloaded. The program creates the baseline file when option 1 is used.

The `docs` folder is also included in the project structure and can be used for project documentation or screenshots if required by the course.

## Error Handling

The program has basic checks for common problems.

For example, entering text instead of a menu number produces:

```text
Invalid input. Please enter a number.
```

An invalid directory produces:

```text
Directory does not exist.
```

If an integrity check is attempted before a baseline has been created, the program displays:

```text
No baseline exists.
Create a baseline first using option 1.
```

## Limitations

This is an academic project rather than a full security monitoring product.

At the moment:

- The program is command-line based.
- Integrity checks are started manually.
- The baseline is stored in a local text file.
- There is no database.
- There is no background monitoring service.
- There is no graphical interface.
- The baseline file itself should be protected from unauthorized changes in a real-world setup.

## Possible Improvements

If the project is extended later, some useful additions could be:

- A graphical interface
- Automatic periodic checks
- More detailed logs
- Database storage
- Multiple monitored folders
- Email or desktop notifications
- Encrypted baseline storage
- Report export
- User authentication

## Learning Outcomes

This project helped put several Java concepts together in one working application, including classes and objects, encapsulation, collections, file handling, exception handling, recursion, and methods.

It also provided practical experience with SHA-256 hashing and with organizing a Java project into multiple classes instead of putting everything into one file.

## Testing

The project contains a `test-cases.md` file with test scenarios for:

- Creating a baseline
- Displaying a baseline
- Detecting modified files
- Detecting added files
- Detecting deleted files
- Invalid input
- Invalid directory paths
- Missing baseline
- Exiting the application

## Author

**Pola Eekashan**  
**Registration Number: 24BCY10024**

**Project:** File Integrity Monitor
