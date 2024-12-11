import React, { useState } from "react";
import { registerBook } from "../api/bookApi";

const BookRegisterPage: React.FC = () => {

	const [formData, setFormData] = useState({
		title: "",
		author: "",
		information: "",
		quantity: 0,
	});

	// Input 변경 핸들러
	const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
		const { name, value } = e.target;
		setFormData((prevData) => ({
			...prevData,
			[name]: name === "quantity" ? parseInt(value, 10) : value,
		}));
	};

	// Form 제출 핸들러
	const handleSubmit = async (e: React.FormEvent) => {
		e.preventDefault();

		registerBook(formData).then((val) => {
			if (val == 201) {
				setFormData({
					title: "",
					author: "",
					information: "",
					quantity: 0,
				})
			}
		});
	};

	return (
		<section className="container">
			<div className="register-form">
				<form className="register-form" onSubmit={handleSubmit}>
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
					<button type="submit">등록하기</button>
				</form>
			</div>
		</section>
	);
}

export default BookRegisterPage;