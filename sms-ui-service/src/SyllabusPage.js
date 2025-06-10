import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "./SyllabusPage.css";

const SyllabusPage = () => {
  const { subjectName } = useParams(); // Get subject from URL
  const [syllabus, setSyllabus] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSyllabus = async () => {
      try {
        const response = await fetch(`http://localhost:8083/sms/getSyllabus/${subjectName}`);
        if (!response.ok) throw new Error("Failed to fetch syllabus");
        const data = await response.json();

        // Convert JSON structure to array of units & topics
        const syllabusArray = data.flatMap(subject =>
          subject.units.map((unit, index) => ({
            serial: index + 1,
            unit: unit.title,
            topics: unit.topics.join(", ")
          }))
        );

        setSyllabus(syllabusArray.length ? syllabusArray : [{ serial: "-", unit: "No syllabus available", topics: "" }]);
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
      <h2>{subjectName} Syllabus</h2>
      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p className="error">{error}</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>S.No</th>
              <th>Units</th>
              <th>Topics</th>
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