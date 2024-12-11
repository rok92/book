import axios from "axios";

const url = "http://localhost:8080/api/books"

// 책 리스트
export const getAllBooks = async () => {
	const { data } = await axios.get(url);
	return data;
}

// 상세정보
export const getBook = async (id: number) => {
	const { data } = await axios.get(`${url}/${id}`);
	return data;
}

// 등록
export const registerBook = async (formData: Object) => {
	try {
		const response = await axios.post(url, formData, {
			headers: {
				"Content-Type": "application/json",
			},
		});

		alert("도서가 성공적으로 등록되었습니다!");
		return response.status;
	} catch (error) {
		console.error("Error posting data:", error);
		alert("Failed to post data. Please try again.");
	}
}

// 검색
export const findByTitle = async (title: string) => {
	const { data } = await axios.get(`${url}/title/${title}`);
	return data;
}

export const findByAuthor = async (author: string) => {
	const { data } = await axios.get(`${url}/author/${author}`);
	return data;
}

// 수정
export const updateInfo = async (data: Object, id: number) => {
	try {
		const response = await axios.put(`${url}/${id}`, data, {
			headers: {
				"Content-Type": "application/json",
			},
		});

		alert("도서가 성공적으로 수정되었습니다!");
		console.log(response.status)
		return response.status;
	} catch (error) {
		console.error("Error posting data:", error);
		alert("Failed to post data. Please try again.");
	}
}

// 삭제
export const deleteBook = async (id: number) => {
	const response = await axios.delete(`${url}/${id}`);
	alert("도서가 성공적으로 삭제 되었습니다!");
	return response.status;
}