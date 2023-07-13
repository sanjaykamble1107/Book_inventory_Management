import { Component, OnInit } from '@angular/core';
import { AuthorService } from 'src/app/service/author.service';
import { Book } from 'src/app/Models/book';
import { Publisher } from 'src/app/Models/publisher';
import { HttpErrorResponse } from '@angular/common/http';
import { Author } from 'src/app/Models/Author';
import { AuthService } from 'src/app/Configuration/auth.service';

@Component({
  selector: 'app-get-author',
  templateUrl: './get-author.component.html',
  styleUrls: ['./get-author.component.css'],
})
export class GetAuthorComponent implements OnInit {
  newAuthor: Author = { authorId: 0, lastName: '', firstName: '', photo: '' };
  searchCriteria: { authorId?: number; firstName?: string; lastName?: string } =
    {};
  booksByAuthorCriteria: { authorId: number } = { authorId: 0 };
  booksByAuthorList: Book[] = [];
  data: any[] = [];
  authorList: Author[] = [];
  userRole: any = '';

  // -----------------------
  author: Author = new Author();
  selectedFile!: File;

  fileName: string | undefined;

  path!: string;
  // -----------------------

  //-----------------------

  constructor(
    private authorService: AuthorService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.loadData();
    this.userRole = this.authService.getRoleNumber();
  }

  addAuthor() {
    //-----------------------
    console.log('addddd');

    const fileReader = new FileReader();

    fileReader.onload = (e) => {
      const fileData = fileReader.result;
      const imgPath = `/assets/${this.fileName}`;

      // Save the image path to the variable (e.g., lines.image)

      this.newAuthor.photo = imgPath;
      console.log(this.newAuthor.photo);
      this.authorService.addAuthor(this.newAuthor).subscribe(
        (msg: any) => {
          // alert(msg);
          console.log('Author added successfully');
          // Clear the form
          alert(msg.responseMessage);
          this.newAuthor = {
            authorId: 0,
            lastName: '',
            firstName: '',
            photo: '',
          };
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );

      // Additional logic to handle file upload or further processing
      // For example, you can send the file to a server using HttpClient

      alert('photo added successfully');

      this.path = imgPath;
      alert('path' + this.path);
      //-----------------------
    };
    fileReader.readAsDataURL(this.selectedFile);
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.fileName = this.selectedFile.name;
  }

  loadData() {
    this.authorService.getData().subscribe(
      (response: any[]) => {
        this.data = response;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        // alert(errorMessage);
      }
    );
  }

  isDataArray(): boolean {
    return Array.isArray(this.data);
  }

  searchAuthors() {
    this.authorService
      .searchAuthors(
        this.searchCriteria.authorId,
        this.searchCriteria.firstName,
        this.searchCriteria.lastName
      )
      .subscribe(
        (authors: any) => (this.authorList = [authors]),
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
  }

  getBooksByAuthor() {
    this.authorService
      .getBooksByAuthor(this.booksByAuthorCriteria.authorId)
      .subscribe(
        (books) => {
          this.booksByAuthorList = books;
          console.log(books);
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
  }
}
