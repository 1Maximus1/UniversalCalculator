type Butto = {
  content: string;
  link: string;
};

export default interface IOptions {
  buttons: Butto[];
  getValue: any;
}
