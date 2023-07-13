export class bookReview {

            isbn!: string;
            reviewerID!: number;
            rating!: number;
            comments!: String;

    constructor() {
        this.isbn = "";
        this.reviewerID = 0;
        this.rating = 0;
        this.comments = "";
    }
}
