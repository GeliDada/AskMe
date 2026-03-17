import { render, screen } from '@testing-library/react';
import App from './App';

test('renders AskMe title', () => {
  render(<App />);
  const titleElement = screen.getByText(/AskMe/i);
  expect(titleElement).toBeInTheDocument();
});
