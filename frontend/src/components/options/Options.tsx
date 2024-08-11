import React, { FC } from "react";
import Button from "../../ui/Button/Button";
import IOptions from "./IOptions";
const Options: FC<IOptions> = ({ buttons, getValue }) => {
  return (
    <div>
      {buttons.map(button => (
        <Button
          style={{ marginRight: "10px" }}
          key={button.link}
          onClick={() => getValue(button.link)}
        >
          {button.content}
        </Button>
      ))}
    </div>
  );
};

export default Options;
