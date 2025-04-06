# 📚 Course Scheduler (Java + Derby DB + Swing UI)

This was a full-stack Java desktop application created for managing course scheduling across semesters. It uses **Apache Derby (Java DB)** for persistent storage and **Java Swing** for a GUI interface. I used NetBeans' Design tab to build the UI layout, and wrote backend logic to handle scheduling, dropping, and waitlisting.

> ⚠️ It’s been a while since I worked on this project, and I seem to have messed up my database setup. At the moment, I’m unable to connect to the Derby DB due to driver/config issues — but I plan to revisit and resolve this later.

---

## 🧩 Features

### 🎛 Admin Panel

-   **Add Semester:** Create and manage distinct semesters.
-   **Add Course:** Define new courses with code, description, and seat limits.
-   **Add Student:** Register students with their name and ID.
-   **Display Course Roster:** View scheduled and waitlisted students by course.
-   **Drop Student:** Remove a student from all schedules.
-   **Drop Course:** Remove a course and automatically update affected student entries.

### 🎓 Student Panel

-   **Display Available Courses:** Browse course catalog for a selected semester.
-   **Schedule Course:** Attempt to enroll a student — auto-waitlist if full.
-   **Display Schedule:** View current schedule (course + status) for a student.
-   **Drop Course:** Drop a student from a specific course and auto-promote from waitlist.

### 🔁 Backend Logic

-   Enforces **first-come-first-serve** with **timestamps**.
-   Handles **schedule capacity** and **automatic waitlist promotions**.
-   Manages data with clean, layered queries:
    -   `CourseQueries`
    -   `StudentQueries`
    -   `ScheduleQueries`
    -   `SemesterQueries`

---

## ⚙️ Tech Stack

-   **Java SE**
-   **Apache Derby (Java DB)** — Network Server mode
-   **Swing** — for building UI in NetBeans
-   **MVC-style structure** (manual)

---

## 📝 Notes

The project was a great experience in:

-   Managing database connections
-   Building CRUD operations across multiple entities
-   Coordinating UI state with backend logic
-   Using NetBeans' GUI builder for clean and responsive layout

---

## 🧠 Let’s Connect!

**Tej Jaideep Patel**  
B.S. Computer Engineering  
📍 Penn State University  
✉️ tejpatelce@gmail.com  
📞 814-826-5544

---
