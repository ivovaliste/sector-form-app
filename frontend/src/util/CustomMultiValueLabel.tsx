import { components } from "react-select";

export const CustomMultiValueLabel = (props: any) => {

  const cleanLabel = props.data.label.trimStart();
  return <components.MultiValueLabel {...props}>{cleanLabel}</components.MultiValueLabel>;
};