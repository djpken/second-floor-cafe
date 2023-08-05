import * as React from "react";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import Collapse from "@mui/material/Collapse";

import ListItemText from "@mui/material/ListItemText";
import ExpandLess from "@mui/icons-material/ExpandLess";
import ExpandMore from "@mui/icons-material/ExpandMore";
import { Link } from "react-router-dom";

interface props {
  text: any;
}

export default function CustomNavBar({ text }: props) {
  const [open, setOpen] = React.useState(false);

  const handleClick = () => {
    setOpen((prevOpen) => !prevOpen);
  };

  return (
    <List
      sx={{
        width: "100%",
        component: "nav",
        padding: 0,
      }}
    >
      <ListItem button onClick={handleClick}>
        <ListItemText sx={{ pl: 3 }} primary={text.title} />
        {open ? <ExpandLess /> : <ExpandMore />}
      </ListItem>
      <Collapse in={open} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          {text.items.map((item: any) => (
            <ListItem
              button
              key={item.to}
              sx={{ pl: 6, color: "black" }}
              component={Link}
              to={item.to}
            >
              <ListItemText primary={item.h} />
            </ListItem>
          ))}
        </List>
      </Collapse>
    </List>
  );
}
