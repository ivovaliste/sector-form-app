import { Category, Option } from "../types/user";


export const FlattenWithIndent = (categories: Category[], level = 0): Option[] => {
         return categories.flatMap((cat) => [
           {
             value: cat.id,
             label: `${"\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0".repeat(level)}${cat.name}`,
           },
           ...(cat.children ? FlattenWithIndent(cat.children, level + 1) : []),
         ]);
       };