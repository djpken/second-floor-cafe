import { useNavigate } from "react-router-dom";
import React from "react";
import { useEffect } from "react";
import axios from "axios";
import { Button } from "@mui/material";

interface ReturnPageProps {
  children: React.ReactNode;
}
export default function ReturnPage({ children }: ReturnPageProps):JSX.Element {
  const navigate = useNavigate();
  return (
    <Button onClick={() => navigate("/main/section")} sx={style}>
      {children}
    </Button>
  );
}
const style = {
  border: "2px solid black",
  height: "50px",
  width: "100px",
  position: "relative",
  backgroundColor: "#FFFFFF",
  color: "black",
};
