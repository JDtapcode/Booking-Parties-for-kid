INSERT INTO Account (user_id, email, password, role)
VALUES (1, 'nguyenvana@example.com', 'password123', "USER");

INSERT INTO Account (user_id, email, password, role)
VALUES (2, 'tranthib@example.com', 'securepassword', "USER");

INSERT INTO Account (user_id, email, password, role)
VALUES (3, 'levanc@example.com', 'mysecretpass', "HOST");

INSERT INTO Hosts (host_id, user_id, name, phone)
VALUES (1, 1, 'Nguyễn Văn A', '0123456789');

INSERT INTO Hosts (host_id, user_id, name, phone)
VALUES (2, 2, 'Trần Thị B', '0987654321');

INSERT INTO Hosts (host_id, user_id, name, phone)
VALUES (3, 3, 'Lê Văn C', '0369871234');

INSERT INTO Location (location_id, host_id, address, name)
VALUES (1, 1, '123 Đường ABC, Thành phố XYZ', 'Nhà Hàng Hoa Mai');

INSERT INTO Location (location_id, host_id, address, name)
VALUES (2, 2, '456 Đường DEF, Thành phố XYZ', 'Khách Sạn Phượng Hoàng');

INSERT INTO Location (location_id, host_id, address, name)
VALUES (3, 3, '789 Đường GHI, Thành phố XYZ', 'Quán Cà Phê Sương Sương');
   
INSERT INTO Package (package_id, location_id, name, price, description)
VALUES (1, 1, 'Gói Tiệc Cưới Cơ Bản', 5000, 'Gói tiệc cưới bao gồm không gian trang trí cơ bản và dịch vụ ăn uống');

INSERT INTO Package (package_id, location_id, name, price, description)
VALUES (2, 2, 'Gói Tiệc Cưới Luxury', 10000, 'Gói tiệc cưới cao cấp với trang trí sang trọng và dịch vụ hoàn hảo');

INSERT INTO Package (package_id, location_id, name, price, description)
VALUES (3, 3, 'Gói Tiệc Cưới Truyền Thống', 3000, 'Gói tiệc cưới theo phong cách truyền thống với không gian ấm cúng và lễ hội vui vẻ');


INSERT INTO Service (service_id, location_id, name, price, description) VALUES
(1, 1, 'Cho thuê địa điểm', 3000.00, 'Cho thuê địa điểm tổ chức lễ cưới và tiệc tiếp khách');

INSERT INTO Service (service_id, location_id, name, price, description) VALUES
(2, 1, 'Dịch vụ tiệc', 5000.00, 'Dịch vụ ẩm thực đầy đủ cho buổi tiệc cưới');

INSERT INTO Service (service_id, location_id, name, price, description) VALUES
(3, 1, 'Trang trí hoa', 1500.00, 'Các bài trí hoa đẹp mắt cho trang trí');


ALTER TABLE Service
    ADD COLUMN image_url VARCHAR(1000);