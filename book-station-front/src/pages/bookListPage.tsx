import React, { useState } from "react";
import { useEffect } from "react";
import { findByAuthor, findByTitle, getAllBooks } from "../api/bookApi";
import { Book } from "../types/book";
import { useNavigate } from "react-router-dom";

import Plus from "../assets/images/plus.png";

const BookListPage: React.FC = () => {

	const [books, setBooks] = useState<Book[]>([]);
	const [currentPage, setCurrentPage] = useState<number>(1);
	const [selectedOption, setSelectedOption] = useState("");
	const [searchQuery, setSearchQuery] = useState<string>("");
	const itemsPerPage = 10;

	const navigate = useNavigate();

	useEffect(() => {
		getAllBooks().then((val) => {
			setBooks(val);
		});
	}, []);

	const goTo = (url: string, id?: number) => {
		if (id !== undefined) {
			navigate(url, { state: { id } });
		} else {
			navigate(url)
		}
	}

	// 페이지네이션 로직
	const indexOfLastItem = currentPage * itemsPerPage;
	const indexOfFirstItem = indexOfLastItem - itemsPerPage;
	const currentItems = books.slice(indexOfFirstItem, indexOfLastItem);

	// 페이지 번호 생성
	const totalPages = Math.ceil(books.length / itemsPerPage);
	const pageNumbers = [];
	for (let i = 1; i <= totalPages; i++) {
		pageNumbers.push(i);
	}

	const handlePageChange = (pageNumber: number) => {
		setCurrentPage(pageNumber);
	}


	const handleSelected = (e: any) => {
		setSelectedOption(e.target.value);
	}

	const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setSearchQuery(e.target.value);
	};


	const findBooks = async (select: string, search: string) => {
		if (select === "1") {
			findByTitle(search).then((val) => {
				setBooks(val);
			})
		} else if (select === "2") {
			findByAuthor(search).then((val) => {
				setBooks(val);
			})
		} else {
			getAllBooks().then((val) => {
				setBooks(val);
			});
		}
	}

	const handleSubmit = (e: React.FormEvent) => {
		e.preventDefault(); // 페이지 새로 고침 방지
		findBooks(selectedOption, searchQuery); // 검색
	};


	return (
		<div className="container">
			<div className="mb-3 d-flex justify-content-between add-book">
				<form className="d-flex" role="search" onSubmit={handleSubmit}>
					<select value={selectedOption} onChange={handleSelected}>
						<option value="">선택</option>
						<option value="1">도서명</option>
						<option value="2">저자</option>
					</select>
					<input className="form-control me-2" value={searchQuery} onChange={handleSearchChange} type="search" placeholder="Search" aria-label="Search" />
					<button className="btn btn-outline-success" type="submit">Search</button>
				</form>
				<img onClick={() => goTo("/books/bookRegister")} src={Plus} width={40} />
			</div>
			<div className="card-container">
				{currentItems.map(item => (
					<div onClick={() => goTo("/books", item.id)} className="card">
						<div className="book_img">
							{item.title}
						</div>
						<div className="card-body">
							<h5 className="card-title">{item.title}</h5>
							<div>
								<p className="card-text">저자: {item.author}</p>

							</div>
						</div>
					</div>
				))}
			</div>

			<div className="mt-5">
				<ul className="pagination page-item">
					<li className="page-item">
						<a onClick={() => handlePageChange(currentPage > 1 ? currentPage - 1 : currentPage)} className="page-link">&laquo;</a>
					</li>
					{pageNumbers.map(number => (
						<li className="page-item"><a onClick={() => handlePageChange(number)} className="page-link">{number}</a></li>
					))}
					<li className="page-item">
						<a onClick={() => handlePageChange(currentPage < pageNumbers.length ? currentPage + 1 : currentPage)} className="page-link">&raquo;</a>
					</li>
				</ul>
			</div>
		</div>
	)
}

export default BookListPage;