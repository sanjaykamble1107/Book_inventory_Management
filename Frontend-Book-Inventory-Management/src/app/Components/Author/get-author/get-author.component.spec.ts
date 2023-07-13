import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAuthorComponent } from './get-author.component';

describe('GetAuthorComponent', () => {
  let component: GetAuthorComponent;
  let fixture: ComponentFixture<GetAuthorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetAuthorComponent]
    });
    fixture = TestBed.createComponent(GetAuthorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
