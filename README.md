# code-plagiarism-detector
Java-based tool for detecting similarity and potential plagiarism between source code submissions.

# Code Plagiarism Detector

A Java-based, menu-driven application designed to detect potential code plagiarism and identify characteristics associated with AI-generated source code.

# Overview

The **Code Plagiarism Detector** provides two detection modes:

1. **AI Detector** – uses Regex-based pattern matching to analyze source code for characteristics commonly associated with AI-generated code.
2. **Database Detector** – compares submitted source code with previously stored code submissions and generates a similarity score.

The project is designed for **Java source code** and uses MySQL for storing and retrieving code submissions.

## Features

* Menu-driven interface
* Regex-based AI-generated code detection
* Database-based plagiarism detection
* Code tokenization
* Code fingerprinting
* Pattern analysis
* Abstract Syntax Tree (AST) analysis
* Similarity score generation
* MySQL database integration
* JDBC connectivity
* Java source code support

## Detection Methods

### 1. AI Detector

The AI Detector uses **Regular Expressions (Regex)** to identify specific coding patterns and characteristics that may be associated with AI-generated code.

The system analyzes the submitted source code for predefined patterns rather than using a machine-learning model.

### 2. Database Detector

The Database Detector compares the submitted code with previously stored code in the MySQL database.

The detection process includes:

* **Tokenization** – breaks source code into meaningful tokens for comparison.
* **Fingerprinting** – creates representations of code patterns to help identify similarities.
* **Pattern Analysis** – examines recurring patterns and structures within the code.
* **AST Analysis** – analyzes the structural representation of the source code.
* **Similarity Scoring** – calculates a similarity score based on the detected similarities.

## Technologies Used

* **Java**
* **Maven**
* **MySQL**
* **JDBC**
* **Regular Expressions (Regex)**
* **Tokenization**
* **Fingerprinting**
* **AST Analysis**

## Database

The project uses **MySQL** to store and retrieve previously submitted source code for plagiarism comparison.

Database credentials should be configured locally and **should not be committed to the repository**.

## Future Improvements

* Improve Regex-based AI-generated code detection
* Enhance detection of modified or obfuscated code
* Improve similarity scoring
* Support additional programming languages
* Add a graphical user interface
* Incorporate more advanced code-structure analysis

## Author

**Vanya Srivastava**

A Java-based project developed as part of my programming and software development work.
