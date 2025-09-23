import React from "react";
import { Control, Controller } from "react-hook-form";
import Select, { GroupBase } from "react-select";

type Option = {
  value: string | number;
  label: string;
};

type MultiSelectProps = {
  control: Control<any>;
  name: string;
  label: string;
  groupedOptions: GroupBase<Option>[]; // ✅ use react-select’s GroupBase
};

export const MultiSelect: React.FC<MultiSelectProps> = ({
  control,
  name,
  label,
  groupedOptions,
}) => {
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
            options={groupedOptions}
            isMulti
            className="text-sm mb-2"
            value={groupedOptions
              .flatMap((group) => group.options)
              .filter((opt) => field.value?.includes(opt.value))}
            onChange={(selected) =>
              field.onChange((selected as Option[]).map((s) => s.value))
            }
          />
        )}
      />
    </div>
  );
};
