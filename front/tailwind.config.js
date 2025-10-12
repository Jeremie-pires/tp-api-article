/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          DEFAULT: '#6909DB',
          dark: '#361DDE',
        },
        secondary: {
          DEFAULT: '#DB25B6',
          light: '#B326DB',
        },
        accent: {
          pink: '#E0ADB7',
          purple: '#C988DB',
        }
      },
    },
  },
  plugins: [],
}
