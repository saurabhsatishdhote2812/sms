import React from "react";
import { useNavigate } from "react-router-dom";
import "./SubjectCards.css";

const subjects = [
  {
    name: "Mathematics",
    icon: "🧮",
    description: "Numbers, algebra, geometry, and more."
  },
  {
    name: "Science",
    icon: "🔬",
    description: "Physics, chemistry, biology, and experiments."
  },
  {
    name: "English",
    icon: "📚",
    description: "Literature, grammar, and communication."
  },
  {
    name: "Geography",
    icon: "🌍",
    description: "Earth, maps, and environments."
  },
  {
    name: "History",
    icon: "🏺",
    description: "Events, civilizations, and cultures."
  }
];

const SubjectCard = ({ subject }) => {
  const navigate = useNavigate();

  return (
    <button
      className="subject-card"
      onClick={() => navigate(`/syllabus/${subject.name}`)}
      aria-label={`View syllabus for ${subject.name}`}
      tabIndex={0}
    >
      <span className="subject-icon" aria-hidden="true">{subject.icon}</span>
      <h2>{subject.name}</h2>
      <p className="subject-desc">{subject.description}</p>
    </button>
  );
};

const SubjectCards = () => (
  <div className="subject-cards-page">
    <h1 className="title">WELCOME TO SCHOOL MANAGEMENT SYSTEM!</h1>
    <p className="subtitle">PLEASE SELECT ANY SUBJECT TO CHECK ITS SYLLABUS</p>
    <div className="subject-cards-container">
      {subjects.map((subject, index) => (
        <SubjectCard subject={subject} key={index} />
      ))}
    </div>
  </div>
);

export default SubjectCards;