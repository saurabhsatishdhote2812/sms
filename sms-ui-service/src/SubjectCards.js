import React from "react";
import { useNavigate } from "react-router-dom";
import "./SubjectCards.css";

const subjects = [
  "Mathematics",
  "Science",
  "English",
  "Geography",
  "History",
];

const SubjectCard = ({ name }) => {
  const navigate = useNavigate();
  
  return (
    <div className="subject-card" onClick={() => navigate(`/syllabus/${name}`)}>
      <h2>{name}</h2>
    </div>
  );
};

const SubjectCards = () => (
  <div className="subject-cards-page">
    <h1 className="title">WELCOME TO SCHOOL MANAGEMENT SYSTEM!</h1>
    <p className="subtitle">PLEASE SELECT ANY SUBJECT TO CHECK ITS SYLLABUS</p>
    <div className="subject-cards-container">
      {subjects.map((subject, index) => (
        <div className={`subject-card-wrapper ${index < 3 ? "column-one" : "column-two"}`} key={index}>
          <SubjectCard name={subject} />
        </div>
      ))}
    </div>
  </div>
);

export default SubjectCards;