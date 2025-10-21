# Resume System

## Overview
Resume System is a next-generation Resume Building & Career Ecosystem designed for students and professionals. Users can generate dynamic, verified resumes automatically based on real achievements from internships, courses, hackathons, and projects. It allows real-time updates, secure authentication, and PDF downloads.

---

## Features
- **User Registration & Login** with JWT authentication.
- **Resume Management**: Add and edit personal details, skills, projects, internships, and certificates.
- **Dynamic Resume Generation**: Automatically updates resumes based on user input.
- **PDF Download**: Download professional resumes as PDF files.
- **Responsive UI**: Clean and mobile-friendly interface for seamless usage.
- **Role-based Users**: Multiple roles like Software Developer, Frontend Developer, Backend Developer, Full Stack Developer, DevOps Engineer.

---

## Tech Stack
- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Token)
- **Build Tool**: Maven
- **Server**: Eclipse + Tomcat

---

## Installation

1. Clone the repository:
(git clone https://github.com/your-username/resume-system.git)

2. Open the project in Eclipse.

3. Configure MySQL database:

Create a database named resume_system.

Update application.properties with your DB credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/resume_system
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

4.Run the Spring Boot application:

ResumeSystemApplication.java


5. Access the frontend pages:

register.html

upload.html

dashboard.html

Usage

1. Register a new user

Select role from dropdown

Automatically logged in after registration

2. Login

Enter email and password

JWT token stored in localStorage

3. Upload Resume Details

Enter skills, projects, internships, and certificates in JSON format

Click “Submit Details” to save

4. Dashboard

View uploaded details

Download resume as PDF

Logout functionality



JSON Format Examples

Projects

[
  {
    "title": "Resume System",
    "description": "A web platform for managing resumes",
    "techStack": "Java, Spring Boot, MySQL, HTML, JS",
    "link": "https://github.com/sumit/resume-system"
  }
]


Skills

[
  { "skillName": "Java", "proficiencyLevel": "Expert" },
  { "skillName": "Spring Boot", "proficiencyLevel": "Intermediate" }
]


Internships

[
  {
    "role": "Cybersecurity Intern",
    "company": "Cybersena India Pvt Ltd",
    "startDate": "2024-06-01",
    "endDate": "2024-07-30",
    "contribution": "Worked on cybersecurity tasks and report automation."
  }
]


Certificates

[
  {
    "courseName": "Java Full Stack Development",
    "platform": "JSpiders",
    "completionDate": "2024-11-01",
    "certificateLink": "https://example.com/java-cert"
  }
]

Screenshots
<img width="1920" height="1080" alt="Screenshot (50)" src="https://github.com/user-attachments/assets/6c139a43-817b-4ee8-b676-0de40aeda849" />
<img width="1920" height="1080" alt="Screenshot (51)" src="https://github.com/user-attachments/assets/d36696dd-ada8-4603-83ff-086c7bfde861" />
<img width="1920" height="1080" alt="Screenshot (52)" src="https://github.com/user-attachments/assets/2d5e54b6-7656-46f2-b280-cc7553b15a3e" />
<img width="1920" height="1080" alt="Screenshot (53)" src="https://github.com/user-attachments/assets/39c8c89b-e533-4945-956e-5a09cf7a4d75" />




Future Enhancements

Integrate AI to auto-suggest resume improvements.

Multi-role admin panel to verify projects and skills.

Real-time collaboration for resume editing.

License

This project is for educational purposes and trial task submission.

Contact

Author: Prajyot kanagale

Email: prajyotkanagale1008@gmail.com

GitHub: https://github.com/prajyotk23/resume-system


---------------------------------------------------------------------------------------------------------------------------------------------------------
