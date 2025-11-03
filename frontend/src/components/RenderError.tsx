export const RenderError = (error?: { message?: string }) => {
  if (!error) return null;
  return <p className="text-red-500 text-sm text-left mt-1">{error.message}</p>;
};