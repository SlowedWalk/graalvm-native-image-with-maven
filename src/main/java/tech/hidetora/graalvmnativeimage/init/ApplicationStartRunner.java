package tech.hidetora.graalvmnativeimage.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.hidetora.graalvmnativeimage.entity.Customer;
import tech.hidetora.graalvmnativeimage.entity.Invoice;
import tech.hidetora.graalvmnativeimage.entity.Revenue;
import tech.hidetora.graalvmnativeimage.entity.STATUS;
import tech.hidetora.graalvmnativeimage.repository.CustomerRepository;
import tech.hidetora.graalvmnativeimage.repository.InvoiceRepository;
import tech.hidetora.graalvmnativeimage.repository.RevenueRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartRunner implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;
    private final RevenueRepository revenueRepository;
    @Override
    public void run(String... args) throws Exception {
       log.info("Application started with command-line arguments: " + String.join(",", args));


        log.info("Saving customers to the database...");
        Customer c1 = Customer.builder().id(UUID.fromString("3958dc9e-712f-4377-85e9-fec4b6a6442a")).name("Delba de Oliveira").email("delba@oliveira.com").imageUrl("/customers/delba-de-oliveira.png").build();
        Customer c2 = Customer.builder().id(UUID.fromString("3958dc9e-742f-4377-85e9-fec4b6a6442a")).name("Lee Robinson").email("lee@robinson.com").imageUrl("/customers/lee-robinson.png").build();
        Customer c3 = Customer.builder().id(UUID.fromString("3958dc9e-737f-4377-85e9-fec4b6a6442a")).name("Hector Simpson").email("hector@simpson.com").imageUrl("/customers/hector-simpson.png").build();
        Customer c4 = Customer.builder().id(UUID.fromString("50ca3e18-62cd-11ee-8c99-0242ac120002")).name("Steven Tey").email("steven@tey.com").imageUrl("/customers/steven-tey.png").build();
        Customer c5 = Customer.builder().id(UUID.fromString("3958dc9e-787f-4377-85e9-fec4b6a6442a")).name("Steph Dietz").email("steph@dietz.com").imageUrl("/customers/steph-dietz.png").build();
        Customer c6 = Customer.builder().id(UUID.fromString("76d65c26-f784-44a2-ac19-586678f7c2f2")).name("Michael Novotny").email("michael@novotny.com").imageUrl("/customers/michael-novotny.png").build();
        Customer c7 = Customer.builder().id(UUID.fromString("d6e15727-9fe1-4961-8c5b-ea44a9bd81aa")).name("Evil Rabbit").email("evil@rabbit.com").imageUrl("/customers/evil-rabbit.png").build();
        Customer c8 = Customer.builder().id(UUID.fromString("126eed9c-c90c-4ef6-a4a8-fcf7408d3c66")).name("Emil Kowalski").email("emil@kowalski.com").imageUrl("/customers/emil-kowalski.png").build();
        Customer c9 = Customer.builder().id(UUID.fromString("CC27C14A-0ACF-4F4A-A6C9-D45682C144B9")).name("Amy Burns").email("amy@burns.com").imageUrl("/customers/amy-burns.png").build();
        Customer c10 = Customer.builder().id(UUID.fromString("13D07535-C59E-4157-A011-F8D2EF4E0CBB")).name("Balazs Orban").email("balazs@orban.com").imageUrl("/customers/balazs-orban.png").build();

        customerRepository.saveAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));

        log.info("Customers saved to the database");


        log.info("Saving invoices to the database...");

        Invoice i1 = Invoice.builder().id(1L).customer(c1).amount(15795).status(STATUS.pending).date(LocalDate.of(2023, 8, 5)).build();
        Invoice i2 = Invoice.builder().id(2L).customer(c2).amount(20348).status(STATUS.pending).date(LocalDate.of(2022, 11, 14)).build();
        Invoice i3 = Invoice.builder().id(3L).customer(c5).amount(3040).status(STATUS.paid).date(LocalDate.of(2022, 10, 29)).build();
        Invoice i4 = Invoice.builder().id(4L).customer(c4).amount(44800).status(STATUS.paid).date(LocalDate.of(2023, 9, 10)).build();
        Invoice i5 = Invoice.builder().id(5L).customer(c6).amount(34577).status(STATUS.pending).date(LocalDate.of(2023, 8, 5)).build();
        Invoice i6 = Invoice.builder().id(6L).customer(c8).amount(54246).status(STATUS.pending).date(LocalDate.of(2023, 7, 16)).build();
        Invoice i7 = Invoice.builder().id(7L).customer(c7).amount(666).status(STATUS.pending).date(LocalDate.of(2023, 6, 27)).build();
        Invoice i8 = Invoice.builder().id(8L).customer(c4).amount(32545).status(STATUS.paid).date(LocalDate.of(2023, 6, 9)).build();
        Invoice i9 = Invoice.builder().id(9L).customer(c5).amount(1250).status(STATUS.paid).date(LocalDate.of(2023, 6, 17)).build();
        Invoice i10 = Invoice.builder().id(10L).customer(c6).amount(8546).status(STATUS.paid).date(LocalDate.of(2023, 6, 7)).build();
        Invoice i11 = Invoice.builder().id(11L).customer(c2).amount(500).status(STATUS.paid).date(LocalDate.of(2023, 8, 19)).build();
        Invoice i12 = Invoice.builder().id(12L).customer(c6).amount(8945).status(STATUS.paid).date(LocalDate.of(2023, 6, 3)).build();
        Invoice i13 = Invoice.builder().id(13L).customer(c3).amount(8945).status(STATUS.paid).date(LocalDate.of(2023, 6, 18)).build();
        Invoice i14 = Invoice.builder().id(12L).customer(c1).amount(8945).status(STATUS.paid).date(LocalDate.of(2023, 10, 4)).build();
        Invoice i15 = Invoice.builder().id(12L).customer(c3).amount(1000).status(STATUS.paid).date(LocalDate.of(2022, 6, 5)).build();

        invoiceRepository.saveAll(List.of(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15));

        log.info("Invoices saved to the database");


        log.info("Saving revenues to the database...");

        Revenue r1 = Revenue.builder().id(1L).revenue(15795).month(Month.JANUARY.toString().substring(0,3)).build();
        Revenue r2 = Revenue.builder().id(2L).revenue(15795).month(Month.FEBRUARY.toString().substring(0,3)).build();
        Revenue r3 = Revenue.builder().id(3L).revenue(15795).month(Month.MARCH.toString().substring(0,3)).build();
        Revenue r4 = Revenue.builder().id(4L).revenue(15795).month(Month.APRIL.toString().substring(0,3)).build();
        Revenue r5 = Revenue.builder().id(5L).revenue(15795).month(Month.MAY.toString().substring(0,3)).build();
        Revenue r6 = Revenue.builder().id(6L).revenue(15795).month(Month.JUNE.toString().substring(0,3)).build();
        Revenue r7 = Revenue.builder().id(7L).revenue(15795).month(Month.JULY.toString().substring(0,3)).build();
        Revenue r8 = Revenue.builder().id(8L).revenue(15795).month(Month.AUGUST.toString().substring(0,3)).build();
        Revenue r9 = Revenue.builder().id(9L).revenue(15795).month(Month.SEPTEMBER.toString().substring(0,3)).build();
        Revenue r10 = Revenue.builder().id(10L).revenue(15795).month(Month.OCTOBER.toString().substring(0,3)).build();
        Revenue r11 = Revenue.builder().id(11L).revenue(15795).month(Month.NOVEMBER.toString().substring(0,3)).build();
        Revenue r12 = Revenue.builder().id(12L).revenue(15795).month(Month.DECEMBER.toString().substring(0,3)).build();

        revenueRepository.saveAll(List.of(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12));

        log.info("Revenues saved to the database");



    }
}
