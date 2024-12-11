import React, { useState, useEffect } from "react";
import { updateInfo } from "../api/bookApi";
import { useLocation, useNavigate } from "react-router-dom";

const BookUpdatePage: React.FC = () => {

	const location = useLocation();
	const { data } = location.state || {};

	const navigate = useNavigate();

	// formData 상태 초기화
	const [formData, setFormData] = useState({
		title: "",
		author: "",
		information: "",
		quantity: 0,
	});

	useEffect(() => {
		if (data) {
			setFormData({
				title: data.title || "",
				author: data.author || "",
				information: data.information || "",
				quantity: data.quantity || 0,
			});
		}
	}, [data]);

	const updateBookInfo = async (e: React.FormEvent) => {
		e.preventDefault();

		updateInfo(formData, data.id).then((val) => {
			if (val === 200) {
				navigate("/");
			}
		});
	}

	// Input 변경 핸들러
	const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
		const { name, value } = e.target;
		setFormData((prevData) => ({
			...prevData,
			[name]: name === "quantity" ? parseInt(value, 10) : value,
		}));
	};

	return (
		<section className="container">
			<div className="register-form">
				<form className="register-form" onSubmit={updateBookInfo}>
					<div className="mb-3">
						<div>Title</div>
						<input
							className="reg-input"
							type="text"
							name="title"
							value={formData.title}
							onChange={handleChange}
							required
						/>
					</div>
					<div className="mb-3">
						<div>Author</div>
						<input
							className="reg-input"
							type="text"
							name="author"
							value={formData.author}
							onChange={handleChange}
							required
						/>
					</div>
					<div className="mb-3">
						<div>Information</div>
						<textarea
							className="reg-input"
							name="information"
							value={formData.information}
							onChange={handleChange}
							required
						/>
					</div>
					<div className="mb-3">
						<div>Quantity</div>
						<input
							className="reg-input"
							type="number"
							name="quantity"
							value={formData.quantity}
							onChange={handleChange}
							required
						/>
					</div>
					<button className="btn btn-primary" type="submit">수정하기</button>
				</form>
			</div>
		</section>
	);
};

export default BookUpdatePage;
