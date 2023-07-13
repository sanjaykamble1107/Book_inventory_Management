import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Category } from 'src/app/Models/category';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-get-category',
  templateUrl: './get-category.component.html',
  styleUrls: ['./get-category.component.css']
})
export class GetCategoryComponent {
  categoryId: number;
  category: Category;

  constructor(private categoryService: CategoryService) {
    this.categoryId = 0;
    this.category = new Category();
  }

  getCategoryDescription() {
    if (this.categoryId) {
      this.categoryService.getCategory(this.categoryId).subscribe(
        (response: Category) => {
          this.category = response;
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
    }
  }
}
