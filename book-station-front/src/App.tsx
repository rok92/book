import './App.css'
import { Route, Routes } from 'react-router-dom';
import BookListPage from './pages/bookListPage';
import BookRegisterPage from "./pages/bookRegisterPage";
import BookDetailPage from "./pages/bookDetailPage";
import Nav from './components/nav';
import BookUpdatePage from './pages/bookUpdatePage';


function App() {

	return (
		<div>
			<Nav />
			<Routes>
				<Route path='/' element={<BookListPage />} />
				<Route path='/books' element={<BookDetailPage />} />
				<Route path='/books/bookRegister' element={<BookRegisterPage />} />
				<Route path='/books/updateBook' element={< BookUpdatePage />} />
			</Routes>
		</div>
	)
}

export default App
