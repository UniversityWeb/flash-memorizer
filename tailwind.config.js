/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./src/main/resources/templates/**/*.{html,js}",
    "./src/main/java/**/*.{java}",
  ],
  theme: {
    extend: {
      colors: {
        primary: "#6366f1",
        secondary: "#8b5cf6",
        danger: "#ef4444",
        success: "#10b981",
        warning: "#f59e0b",
      },
    },
  },
  plugins: [],
}
