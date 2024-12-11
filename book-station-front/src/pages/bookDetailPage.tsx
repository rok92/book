import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { deleteBook, getBook } from "../api/bookApi";

const BookDetailPage: React.FC = () => {
	const location = useLocation();
	const { id } = location.state;

	const navigate = useNavigate();

	const goTo = (url: string, data: Object) => {
		navigate(url, { state: { data } })
	}

	const [bookInfo, setBookInfo] = useState({
		title: "",
		author: "",
		information: "",
		quantity: 0
	})

	useEffect(() => {
		getBook(id).then((data) => {
			setBookInfo(data);
		})
	}, [])

	const deleteBookWithId = (id: number) => {
		deleteBook(id).then((val) => {
			if (val === 200) {
				navigate("/");
			}
		})
	}

	return (
		<section className="wrap-detail">
			<div className="detail-box">
				<div className="book-img">
					{bookInfo.title}
				</div>
				<div className="info-box">
					<div>
						도서명: {bookInfo.title}
					</div>
					<div>
						저자: {bookInfo.author}
					</div>
					<div>
						정보: {bookInfo.information}
					</div>
					<div>
						남은 수량: {bookInfo.quantity}
					</div>
					<div className="d-flex">
						<button onClick={() => goTo("/books/updateBook", bookInfo)} className="btn btn-primary modi">수정</button>
						<button onClick={() => deleteBookWithId(id)} className="btn btn-danger">삭제</button>
					</div>
				</div>
			</div>

		</section>
	)
}

export default BookDetailPage;