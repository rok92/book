export interface Book {
	id: number;
	title: string;
	author: string;
	information: String;
	quantity: number;
}

export interface BookPayload {
	title: string;
	author: string;
	quantity: number;
}
