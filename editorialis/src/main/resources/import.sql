insert into categories (name) values ('Mathematics')
insert into categories (name) values ('Neuroscience')
-- insert into categories (name) values ('Neurology')
-- insert into categories (name) values ('Microsystems & Nanoengineering')
-- insert into categories (name) values ('Chemical biology')
-- insert into categories (name) values ('Solid mechanics')
-- insert into categories (name) values ('Human behaviour')
-- insert into categories (name) values ('Pharmacology')
-- insert into categories (name) values ('Biotechnology')

insert into authors (first_Name,last_Name, email) values ('John', 'Doe', 'john.doe@example.com')
insert into authors (first_Name,last_Name, email) values ('Audrey', 'Smith', 'au.smith@example.com')
insert into authors (first_Name,last_Name, email) values ('Thomas', 'Klein', 'thom.klein@example.com')
insert into authors (first_Name,last_Name, email) values ('Jan', 'Novak', 'jan.novak@example.com')
insert into authors (first_Name,last_Name, email) values ('Lena', 'Welsch', 'lena.welsch@example.com')
insert into authors (first_Name,last_Name, email) values ('Petr', 'Johnson', 'pjohnson@example.com')
insert into authors (first_Name,last_Name, email) values ('Andrew', 'Scott', 'and.scott@example.com')
insert into authors (first_Name,last_Name, email) values ('Edward', 'Sullivan', 'ed.sullivan@example.com')
insert into authors (first_Name,last_Name, email) values ('Michal', 'Muller', 'michal.muller1@example.com')
insert into authors (first_Name,last_Name, email) values ('Klara', 'Novotna', 'knovotna@example.com')

insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Martha', 'Clokie', 'ma.clokie@example.com', 1, true)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Sarah', 'DeWeerdt', 'sdeweerdt@example.com', 1, true)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Gianpaolo', 'Carosi', 'gian.carosi@example.com', 1, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Johannes', 'Overvelde', 'john.overvelde@example.com', 1, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Neil', 'Vasan', 'neil.vasan@example.com', 1, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Lara', 'Pivodic', 'lara.pivodic@example.com', 1, true)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Andrew','Robinson', 'and.robinson@example.com', 1, true)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Conor', 'Purcell', 'con.purcell@example.com', 1, false )
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Chang ', 'Liu', 'chang.liu@example.com', 1, false )
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Yang', 'Yang', 'yang.yang@example.com', 1, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Dan', 'Fox', 'd.fox@example.com', 2, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Elizabeth','Gibney', 'el.gibney@example.com', 2, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Conor', 'Purcell', 'con.purcell1@example.com', 2, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Orsolya', 'Barabas', 'ors.barabas@example.com', 2, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Ilaria', 'Malanchi', 'ilaria.malanchi@example.com', 2, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Edgart', 'Bauer', 'ed.bauer@example.com', 2, false)
insert into reviewers (first_Name, last_Name, email, category_id, on_Review) values ('Chris', 'Woolston', 'chris.woolston1@example.com', 2, false)



insert into editors (first_Name, last_Name, email, category_id, password) values ('Zachary', 'Feinstein', 'editor.editorialis@example.com', 1, '$2y$10$U6g6Ymv9sIfa.vWFeu/wFus9cL1ZsN.z3N0rm4ziqy2EwjMEbW4iG')
insert into editors (first_Name, last_Name, email, category_id) values ('Leslie', 'Greengard', 'leslie.greengard@example.com', 2)
insert into editors (first_Name, last_Name, email, category_id) values ('Mark', 'Girolami', 'mark.girolami@example.com', 1)
-- insert into editors (first_Name, last_Name, email, category_id) values ('Johannes', 'Wiesel', 'john.wiesel@example.com', 4)
-- insert into editors (first_Name, last_Name, email, category_id) values ('Gábor', 'Pataki', 'gpataki@example.com', 5)
-- insert into editors (first_Name, last_Name, email, category_id) values ('Mark', 'Wilson', 'mark.wilson@example.com', 6)
-- insert into editors (first_Name, last_Name, email, category_id) values ('Amnon', 'Ta-Shma', 'amnontashma@example.com', 7)
-- insert into editors (first_Name, last_Name, email, category_id) values ('Venkata', 'Koppula', 'venkoppula@example.com', 8)
-- insert into editors (first_Name, last_Name, email, category_id) values ('Brent', 'Waters', 'brntwaters@example.com', 9)

insert into manuscripts (title, category_id, when_uploaded, closed, manuscript_status, reviewed, reviewer_id, editor_id, when_assigned_to_editor) values ('The metabolic face of migraine — from pathophysiology to treatment.', 1,  {ts '2019-08-17 18:47:52'}, false, 'PENDING', false, 1, 1, {ts '2019-08-17 18:47:52'})
insert into manuscripts (title, category_id, when_uploaded, closed, manuscript_status, reviewed, reviewer_id, editor_id, when_assigned_to_editor, when_assigned_to_reviewer) values ('Finding Determinant Forms of Certain Hybrid Sheffer Sequences.', 1,    {ts '2019-04-17 18:47:52'}, false, 'PEER_REVIEW', false, 2, 1, {ts '2019-04-17 18:47:52'}, {ts '2019-04-19 8:07:52'})
insert into manuscripts (title, category_id, when_uploaded, manuscript_status, closed, reviewed, reviewer_id, editor_id, when_assigned_to_editor, when_assigned_to_reviewer, when_completed_reviews) values ('Modelling roasting coffee beans using mathematics: now full-bodied and robust.', 1, {ts '2019-05-21 14:29:37'}, 'PRINCIPAL_REVIEW', true, false, 3, 1, {ts '2019-05-21 14:29:37'}, {ts '2019-05-22 10:29:37'}, {ts '2019-06-03 14:29:37'})
insert into manuscripts (title, category_id, when_uploaded, manuscript_status, closed, reviewed, reviewer_id, editor_id, when_assigned_to_editor, when_assigned_to_reviewer, when_completed_reviews, when_accepted) values ('Diagnosis and management of Guillain–Barré syndrome in ten steps.', 1, {ts '2019-07-21 10:39:37'}, 'ACCEPTED', true, true, 4, 1, {ts '2019-07-21 10:39:37'}, {ts '2019-07-21 17:09:31'}, {ts '2019-08-09 11:49:27'}, {ts '2019-08-11 14:19:39'})
insert into manuscripts (title, category_id, when_uploaded, manuscript_status, closed, reviewed, reviewer_id, editor_id, when_assigned_to_editor, when_assigned_to_reviewer, when_completed_reviews, when_rejected) values ('Axonal transport and neurological disease.', 1, {ts '2019-10-21 08:09:37'}, 'REJECTED', true, true, 5, 1, {ts '2019-10-21 08:09:37'}, {ts '2019-10-21 18:36:24'}, {ts '2019-11-04 15:30:12'}, {ts '2019-11-05 11:30:02'})
insert into manuscripts (title, category_id, when_uploaded, manuscript_status, closed, reviewed, reviewer_id, editor_id, when_assigned_to_editor, when_assigned_to_reviewer) values ('Scalable integration of nano-, and microfluidics with hybrid two-photon lithography.', 1, {ts '2019-05-21 14:29:37'}, 'PEER_REVIEW', false, false, 6, 1, {ts '2019-05-21 14:29:37'}, {ts '2019-05-21 15:02:43'})
insert into manuscripts (title, category_id, when_uploaded, manuscript_status, closed, reviewed, reviewer_id, editor_id, when_assigned_to_editor, when_assigned_to_reviewer) values ('Structural complementarity facilitates E7820-mediated degradation of RBM39 by DCAF15.', 2, {ts '2019-09-11 15:29:37'}, 'PEER_REVIEW', false, false, 7, 2, {ts '2019-09-11 15:29:37'}, {ts '2019-09-12 09:13:42'})
insert into manuscripts (title, category_id, when_uploaded, manuscript_status, closed, reviewed, editor_id) values ('Memory in aging.', 2, {ts '2019-11-01 14:29:37'}, 'NEW', false, false, 2)
insert into manuscripts (title, category_id, when_uploaded, manuscript_status, closed, reviewed, editor_id) values ('De novo-designed translation-repressing riboregulators for multi-input cellular logic.', 2, {ts '2019-08-03 14:29:37'}, 'NEW', false, false, 2)

insert into manuscript_authors (manuscript_id, author_id) values (1 , 1)
insert into manuscript_authors (manuscript_id, author_id) values (1 , 2)
insert into manuscript_authors (manuscript_id, author_id) values (1 , 3)
insert into manuscript_authors (manuscript_id, author_id) values (2 , 4)
insert into manuscript_authors (manuscript_id, author_id) values (2, 5)
insert into manuscript_authors (manuscript_id, author_id) values (3, 6)
insert into manuscript_authors (manuscript_id, author_id) values (3, 7)
insert into manuscript_authors (manuscript_id, author_id) values (4, 8)
insert into manuscript_authors (manuscript_id, author_id) values (4, 9)
insert into manuscript_authors (manuscript_id, author_id) values (4, 10)
insert into manuscript_authors (manuscript_id, author_id) values (5, 3)
insert into manuscript_authors (manuscript_id, author_id) values (5, 5)
insert into manuscript_authors (manuscript_id, author_id) values (6, 2)
insert into manuscript_authors (manuscript_id, author_id) values (6, 8)
insert into manuscript_authors (manuscript_id, author_id) values (7, 8)
insert into manuscript_authors (manuscript_id, author_id) values (7, 8)
insert into manuscript_authors (manuscript_id, author_id) values (7, 8)
insert into manuscript_authors (manuscript_id, author_id) values (8, 8)
insert into manuscript_authors (manuscript_id, author_id) values (9, 8)
insert into manuscript_authors (manuscript_id, author_id) values (9, 8)

insert into reviews(reviewer_id, manuscript_id, reviewers_suggestion, when_uploaded) values (3, 3, 'APPROVE', {ts '2019-06-03 14:29:37'})
insert into reviews(reviewer_id, manuscript_id, reviewers_suggestion, when_uploaded) values (4, 4, 'APPROVE', {ts '2019-08-09 11:49:27'})
insert into reviews(reviewer_id, manuscript_id, reviewers_suggestion, when_uploaded) values (5, 5, 'REJECT',  {ts '2019-11-04 15:30:12'})