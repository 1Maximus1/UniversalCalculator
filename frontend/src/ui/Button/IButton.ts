import { ButtonHTMLAttributes } from "react";

export default interface IButton
  extends ButtonHTMLAttributes<HTMLButtonElement> {
  children: React.ReactNode;
}
