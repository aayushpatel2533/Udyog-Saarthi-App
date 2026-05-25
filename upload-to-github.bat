@echo off
echo ========================================
echo Udyog Saarthi - GitHub Upload Script
echo ========================================
echo.

REM Check if git is installed
where git >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Git is not installed!
    echo Please install Git from: https://git-scm.com/download/win
    echo.
    pause
    exit /b 1
)

echo Git is installed. Proceeding...
echo.

REM Check if already initialized
if exist ".git" (
    echo Git repository already initialized.
    echo.
) else (
    echo Initializing Git repository...
    git init
    echo.
)

REM Add all files
echo Adding all files to Git...
git add .
echo.

REM Create commit
echo Creating commit...
set /p commit_message="Enter commit message (or press Enter for default): "
if "%commit_message%"=="" set commit_message=Initial commit: Udyog Saarthi Android app

git commit -m "%commit_message%"
echo.

REM Get GitHub repository URL
echo.
echo ========================================
echo IMPORTANT: Create a repository on GitHub first!
echo Go to: https://github.com/new
echo Repository name: UdyogSarthi
echo DO NOT initialize with README
echo ========================================
echo.

set /p github_url="Enter your GitHub repository URL (e.g., https://github.com/username/UdyogSarthi.git): "

if "%github_url%"=="" (
    echo ERROR: GitHub URL is required!
    pause
    exit /b 1
)

REM Check if remote already exists
git remote get-url origin >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo Remote 'origin' already exists. Updating...
    git remote set-url origin %github_url%
) else (
    echo Adding remote repository...
    git remote add origin %github_url%
)
echo.

REM Set main branch and push
echo Pushing to GitHub...
git branch -M main
git push -u origin main

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo SUCCESS! Project uploaded to GitHub!
    echo ========================================
    echo.
    echo Your repository: %github_url%
    echo.
) else (
    echo.
    echo ========================================
    echo ERROR: Failed to push to GitHub!
    echo ========================================
    echo.
    echo Possible reasons:
    echo 1. Invalid repository URL
    echo 2. Authentication failed
    echo 3. Repository doesn't exist
    echo.
    echo Please check and try again.
    echo.
)

pause
