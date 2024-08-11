import { ReactNode } from "react";

export default interface IModal {
  visible: boolean;
  children: ReactNode;
}
