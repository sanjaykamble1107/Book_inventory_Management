import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetReviewerComponent } from './get-reviewer.component';

describe('GetReviewerComponent', () => {
  let component: GetReviewerComponent;
  let fixture: ComponentFixture<GetReviewerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetReviewerComponent]
    });
    fixture = TestBed.createComponent(GetReviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
