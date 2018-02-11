# loanfactorywebapp
Spring Boot, H2 DB, JUnit4, Hibernate, Maven, Servlets, Thymeleaf, HTML, CSS

localhost:8080, Please go to "Create Loan", give parameters. After save, you will see the saved loan, if you click
to cell "repayments", you will see the repayments by months.

By LoanFactory, we can create a loan, calculate annuity, and repayment plan by months divided for
principal, interest, IOP and ROP. Loan and Repayment are the 2 domain entities, they are immutable. We can only
create properly loans and repayments from the LoanFactory interface. (It is a small project, and because of the
visibility of the calculation, I am using "double" for prices. In bigger application, or API, I would use BigDecimal
.)

Only display, and save options. For other SpringBoot things, like delete, update, users, logins, ShoppingCarts,
messaging, please visit my other repositories.

