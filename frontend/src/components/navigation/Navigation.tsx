import React from "react";
import Button from "../../ui/Button/Button";
import { Link } from "react-router-dom";
const Navigation = () => {
  return (
    <div style={{ maxWidth: "200px" }}>
      <div
        style={{
          margin: "0 auto",
          padding: "30px 20px 30px",
          display: "flex",
          flexDirection: "column",
        }}
      >
        <Link to='/'>
          <Button style={{ marginBottom: "10px" }}>single matrix</Button>
        </Link>
        <Link to='/multiple'>
          <Button style={{ marginBottom: "10px" }}>double matrix</Button>
        </Link>
      </div>
    </div>
  );
};

export default Navigation;
