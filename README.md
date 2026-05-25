# Udyog Saarthi - Your Career Companion

An Android application designed to help users find jobs, build resumes, discover training institutes, and stay updated on job fairs.

## 📱 Features

### Authentication
- **User Registration** - Create new account with email, phone, and password
- **User Login** - Secure login with email validation
- **Session Management** - Persistent login state

### Core Features
- **Job Listings** - Browse and search available job opportunities
- **Resume Builder** - Create professional resumes with personal info, education, skills, and experience
- **Institutes Directory** - Find ITI, Polytechnic, and Skill Development centers
- **Job Fairs** - Stay updated on upcoming job fair events
- **Document Uploads** - Upload and manage important documents (Aadhar, Resume, Certificates, etc.)

## 🛠️ Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Framework**: Material Components for Android
- **Navigation**: Android Navigation Component
- **View Binding**: Enabled for type-safe view access
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📂 Project Structure

```
app/
├── src/main/
│   ├── java/com/example/udyogsaarthi/
│   │   ├── ui/                    # Activities and Fragments
│   │   │   ├── LoginActivity.kt
│   │   │   ├── RegisterActivity.kt
│   │   │   ├── MainActivity.kt
│   │   │   ├── home/
│   │   │   ├── jobs/
│   │   │   ├── resume/
│   │   │   ├── institutes/
│   │   │   ├── jobfairs/
│   │   │   ├── documents/
│   │   │   └── more/
│   │   ├── model/                 # Data models
│   │   ├── data/                  # Repositories
│   │   └── utils/                 # Utility classes
│   └── res/
│       ├── layout/                # XML layouts
│       ├── navigation/            # Navigation graph
│       ├── menu/                  # Menu resources
│       └── values/                # Colors, strings, themes
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 11 or later
- Android SDK 34
- Gradle 8.2+

### Installation

1. Clone the repository:
```bash
git clone https://github.com/YOUR_USERNAME/UdyogSarthi.git
```

2. Open the project in Android Studio

3. Sync Gradle files:
   - File → Sync Project with Gradle Files

4. Build the project:
   - Build → Rebuild Project

5. Run on emulator or device:
   - Run → Run 'app'

## 🔐 Test Credentials

**Default User:**
- Email: `user@gmail.com`
- Password: `1234`

Or register a new account through the app!

## 📸 Screenshots

*(Add screenshots of your app here)*

## 🎨 Design

- **Theme**: Material Components Light Theme
- **Primary Color**: Blue (#1565C0)
- **UI Pattern**: Navigation Drawer + Bottom Navigation
- **Responsive**: Adapts to different screen sizes

## 📋 Features in Detail

### 1. Job Listings
- Search jobs by title or company
- Filter by job type (Full-time, Part-time, etc.)
- View detailed job descriptions
- Material Design cards with smooth scrolling

### 2. Resume Builder
- Multi-section form (Personal, Education, Skills, Experience)
- Add/remove skills dynamically
- Live preview of formatted resume
- Data persists across configuration changes

### 3. Institutes Directory
- Grid view of training institutes
- Filter by type (ITI, Polytechnic, Skill Center)
- Search by name or courses
- Bottom sheet with detailed information
- Direct call functionality

### 4. Job Fairs
- List of upcoming job fair events
- Registration links for each event
- Date and location information
- One-tap browser navigation

### 5. Document Management
- Upload multiple document types
- Track upload status
- Progress indicators
- File picker integration

## 🔄 Future Enhancements

- [ ] Backend API integration
- [ ] Real-time job notifications
- [ ] Resume PDF export
- [ ] Social media sharing
- [ ] Advanced search filters
- [ ] Bookmark favorite jobs
- [ ] Application tracking
- [ ] Profile management
- [ ] Dark mode support

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Developer

Developed as part of Android development learning project.

## 📞 Contact

For any queries or suggestions, please open an issue on GitHub.

---

**Note**: This app currently uses in-memory storage for user data. Data will be lost when the app is closed. Future versions will include persistent storage with Room database or backend integration.
