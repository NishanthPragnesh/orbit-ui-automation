# 🧪 Orbit UI Automation Tests

Automated UI testing framework for validating the Orbit E-Commerce website using **Selenium**, **Cucumber**, and **TestNG**, powered by **Maven** and integrated with **GitHub Actions CI/CD**.

---

## 🚀 Project Overview

This repository contains end-to-end UI test cases for the Orbit frontend, verifying:

- Page title validation
- Button and section visibility
- Product count and price checks
- Footer content
- Screenshot capturing on failure

Tests are run both **locally** and through **GitHub Actions on push**.

---

## 🛠️ Tech Stack

| Layer              | Technology                         |
|--------------------|-------------------------------------|
| UI Testing         | Selenium WebDriver                  |
| Test Framework     | Cucumber (BDD) + TestNG             |
| Build Tool         | Maven                               |
| CI/CD              | GitHub Actions                      |
| Cloud Deployment   | AWS S3 (Static Web Hosting)         |
| Driver Management  | WebDriverManager (Cross-Platform)   |

---

## ✅ Run Tests Locally

### 1. 📦 Prerequisites

- Java 17+
- Maven 3.6+
- Chrome Browser
- ChromeDriver installed or use WebDriverManager (recommended)

### 2. 🧪 Run the tests

```bash
mvn clean test

