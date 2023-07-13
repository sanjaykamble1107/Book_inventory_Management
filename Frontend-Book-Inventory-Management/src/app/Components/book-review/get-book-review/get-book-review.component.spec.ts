import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetBookReviewComponent } from './get-book-review.component';

describe('GetBookReviewComponent', () => {
  let component: GetBookReviewComponent;
  let fixture: ComponentFixture<GetBookReviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetBookReviewComponent]
    });
    fixture = TestBed.createComponent(GetBookReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
