import React from "react";
import { Control, Controller } from "react-hook-form";
import Select from "react-select";
import { Category, Option } from "../types/user";
import {FlattenWithIndent, CustomMultiValueLabel} from "../util"

type MultiSelectProps = {
  control: Control<any>;
  name: string;
  label: string;
  categories: Category[];
};

export const MultiSelect: React.FC<MultiSelectProps> = ({
  control,
  name,
  label,
  categories,
}) => {
  const options = FlattenWithIndent(categories);


  return (
    <div>
      <label className="block text-left font-medium text-gray-700 mb-2">
        {label}
      </label>
      <Controller
        control={control}
        name={name}
        render={({ field }) => (
          <Select
            options={options}
            isMulti
            hideSelectedOptions={false}
            className="text-left"
            value={options.filter((opt) =>
              field.value?.map(Number).includes(Number(opt.value))
            )}
            onChange={(selected) =>
              field.onChange((selected as Option[]).map((s) => s.value))
            }
            components={{ MultiValueLabel: CustomMultiValueLabel }}
            styles={{
              option: (provided) => ({ ...provided, textAlign: "left" }),
              singleValue: (provided) => ({ ...provided, textAlign: "left" }),
              multiValueLabel: (provided) => ({ ...provided, textAlign: "left" }),
            }}


          />
        )}

      />
    </div>
  );
};
