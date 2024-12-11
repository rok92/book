import { createRoot } from 'react-dom/client'
import { HashRouter } from 'react-router-dom'
import "bootstrap/dist/css/bootstrap.css";
import './index.css'
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
	<HashRouter>
		<App />
	</HashRouter>,
)
