# AskMe

AskMe is a full-stack PDF question-answering app. Users can upload a PDF, ask natural-language questions about the document, and receive concise answers generated through a retrieval-based pipeline.

## Tech Stack

- Frontend: React, Ant Design, Axios
- Backend: Node.js, Express, Multer
- AI / Retrieval: LangChain, OpenAI embeddings, in-memory vector store
- File handling: PDFLoader for PDF parsing

## Features

- Upload one or more PDF files from the browser
- Ask questions about the uploaded document
- Display the conversation history in a simple chat-style interface
- Optional voice input / speech output mode in the frontend

## Project Structure

```text
AskMe/
├── public/
├── src/
│   ├── components/
│   ├── App.js
│   └── index.js
├── server/
│   ├── chat.js
│   ├── server.js
│   ├── package.json
│   ├── .env.example
│   └── uploads/
├── package.json
└── README.md
```

## How to Run Locally

### 1. Install frontend dependencies

```bash
npm install
```

### 2. Install backend dependencies

```bash
cd server
npm install
cd ..
```

### 3. Add your API key

Create a file at `server/.env` and add:

```bash
OPENAI_API_KEY=your_openai_api_key_here
```

The server code also supports `REACT_APP_OPENAI_API_KEY` as a fallback because that was used in the original project.

### 4. Start the app

From the project root:

```bash
npm run dev
```

This starts:

- the React frontend on `http://localhost:3000`
- the Express backend on `http://localhost:5001`

## Notes

- This repository intentionally excludes `node_modules`, `.git`, uploaded PDFs, and private environment files.
- The original uploaded archive contained a real API key in `server/.env`. That file has **not** been included in this GitHub-ready version.
- The backend stores uploaded files in `server/uploads/`.
- This project uses an in-memory vector store, so uploaded document context is not persisted after the server restarts.

## GitHub Upload Checklist

1. Upload this cleaned project folder to a new GitHub repository.
2. Add your own `server/.env` locally after cloning.
3. Run `npm install` in both the root folder and `server/`.
4. Start with `npm run dev`.

## Possible Future Improvements

- Better error handling when no PDF has been uploaded yet
- Support for multiple documents with document selection
- Deployment configuration for Vercel / Render / Railway
- Persistent vector database instead of in-memory storage
- Safer secret management and production-ready environment configuration
