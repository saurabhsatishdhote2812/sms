import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./SyllabusPage.css";

const SyllabusPage = () => {
  const { subjectName } = useParams();
  const navigate = useNavigate();
  const [syllabus, setSyllabus] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSyllabus = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await fetch(`http://localhost:8082/sms/getSyllabus/${subjectName}`);
        if (!response.ok) throw new Error("Failed to fetch syllabus. Please try again later.");
        const data = await response.json();

        const syllabusArray = data.flatMap(subject =>
          subject.units.map((unit, index) => ({
            serial: index + 1,
            unit: unit.title,
            topics: unit.topics.join(", ")
          }))
        );

        setSyllabus(syllabusArray.length ? syllabusArray : []);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchSyllabus();
  }, [subjectName]);

  return (
    <div className="syllabus-container">
      <button className="back-btn" onClick={() => navigate("/subjects")}>← Back to Subjects</button>
      <h2>{subjectName} Syllabus</h2>
      {loading ? (
        <div className="spinner"></div>
      ) : error ? (
        <p className="error" role="alert">{error}</p>
      ) : syllabus.length === 0 ? (
        <p className="empty-state">No syllabus available for this subject.</p>
      ) : (
        <table className="syllabus-table">
          <thead>
            <tr>
              <th scope="col">S.No</th>
              <th scope="col">Units</th>
              <th scope="col">Topics</th>
            </tr>
          </thead>
          <tbody>
            {syllabus.map((item, index) => (
              <tr key={index}>
                <td>{item.serial}</td>
                <td>{item.unit}</td>
                <td>{item.topics}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default SyllabusPage;