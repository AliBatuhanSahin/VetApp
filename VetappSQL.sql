PGDMP  "        	            {            Vetapp    16.1    16.1 )    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16980    Vetapp    DATABASE     |   CREATE DATABASE "Vetapp" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_Turkey.1254';
    DROP DATABASE "Vetapp";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    19049    animal    TABLE     >  CREATE TABLE public.animal (
    id bigint NOT NULL,
    animal_breed character varying(255),
    animal_colour character varying(255),
    animal_date_of_birth date,
    animal_gender character varying(255),
    animal_name character varying(255),
    animal_species character varying(255),
    customer_id bigint
);
    DROP TABLE public.animal;
       public         heap    postgres    false    4            �            1259    19087 
   animal_seq    SEQUENCE     t   CREATE SEQUENCE public.animal_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.animal_seq;
       public          postgres    false    4            �            1259    19056    appointment    TABLE     �   CREATE TABLE public.appointment (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id bigint,
    doctor_id bigint
);
    DROP TABLE public.appointment;
       public         heap    postgres    false    4            �            1259    19088    appointment_seq    SEQUENCE     y   CREATE SEQUENCE public.appointment_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.appointment_seq;
       public          postgres    false    4            �            1259    19061    available_date    TABLE     n   CREATE TABLE public.available_date (
    id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false    4            �            1259    19089    available_date_seq    SEQUENCE     |   CREATE SEQUENCE public.available_date_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.available_date_seq;
       public          postgres    false    4            �            1259    19066    customer    TABLE     !  CREATE TABLE public.customer (
    id bigint NOT NULL,
    customer_address character varying(255),
    customer_city character varying(255),
    customer_mail character varying(255) NOT NULL,
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255)
);
    DROP TABLE public.customer;
       public         heap    postgres    false    4            �            1259    19090    customer_seq    SEQUENCE     v   CREATE SEQUENCE public.customer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.customer_seq;
       public          postgres    false    4            �            1259    19073    doctor    TABLE       CREATE TABLE public.doctor (
    id bigint NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_mail character varying(255) NOT NULL,
    doctor_name character varying(255) NOT NULL,
    doctor_phone character varying(255) NOT NULL
);
    DROP TABLE public.doctor;
       public         heap    postgres    false    4            �            1259    19091 
   doctor_seq    SEQUENCE     t   CREATE SEQUENCE public.doctor_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.doctor_seq;
       public          postgres    false    4            �            1259    19080    vaccine    TABLE     �   CREATE TABLE public.vaccine (
    id bigint NOT NULL,
    vaccine_code character varying(255),
    vaccine_name character varying(255),
    vaccine_finish_date date,
    vaccine_start_date date,
    animal_id bigint
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false    4            �            1259    19092    vaccine_seq    SEQUENCE     u   CREATE SEQUENCE public.vaccine_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.vaccine_seq;
       public          postgres    false    4            �          0    19049    animal 
   TABLE DATA           �   COPY public.animal (id, animal_breed, animal_colour, animal_date_of_birth, animal_gender, animal_name, animal_species, customer_id) FROM stdin;
    public          postgres    false    215   �.       �          0    19056    appointment 
   TABLE DATA           Q   COPY public.appointment (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public          postgres    false    216   �/       �          0    19061    available_date 
   TABLE DATA           G   COPY public.available_date (id, available_date, doctor_id) FROM stdin;
    public          postgres    false    217   �/       �          0    19066    customer 
   TABLE DATA           u   COPY public.customer (id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    218   10       �          0    19073    doctor 
   TABLE DATA           i   COPY public.doctor (id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    219   &1       �          0    19080    vaccine 
   TABLE DATA           u   COPY public.vaccine (id, vaccine_code, vaccine_name, vaccine_finish_date, vaccine_start_date, animal_id) FROM stdin;
    public          postgres    false    220   %2       �           0    0 
   animal_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.animal_seq', 101, true);
          public          postgres    false    221            �           0    0    appointment_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.appointment_seq', 101, true);
          public          postgres    false    222            �           0    0    available_date_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.available_date_seq', 151, true);
          public          postgres    false    223            �           0    0    customer_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.customer_seq', 101, true);
          public          postgres    false    224            �           0    0 
   doctor_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.doctor_seq', 101, true);
          public          postgres    false    225            �           0    0    vaccine_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.vaccine_seq', 101, true);
          public          postgres    false    226            4           2606    19055    animal animal_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    215            6           2606    19060    appointment appointment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    216            8           2606    19065 "   available_date available_date_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    217            :           2606    19072    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    218            <           2606    19079    doctor doctor_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    219            >           2606    19086    vaccine vaccine_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    220            @           2606    19098 '   appointment fk2kkeptdxfuextg5ch7xp3ytie    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie;
       public          postgres    false    216    4660    215            ?           2606    19093 "   animal fk6pvxm5gfjqxclb651be9unswe    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 L   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk6pvxm5gfjqxclb651be9unswe;
       public          postgres    false    218    215    4666            B           2606    19108 *   available_date fkk0d6pu1wxarsoou0x2e1cc2on    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 T   ALTER TABLE ONLY public.available_date DROP CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on;
       public          postgres    false    217    4668    219            C           2606    19113 #   vaccine fkne3kmh8y5pcyxwl4u2w9prw6j    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j;
       public          postgres    false    4660    215    220            A           2606    19103 '   appointment fkoeb98n82eph1dx43v3y2bcmsl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl;
       public          postgres    false    216    4668    219            �   �   x��ο�0����}���R�+Y41`��rҋ4��)h�������7��`����Q�)@�kK-(�����4찦�<`�/`��ej��}K��ݯ0Z
9j򒛵�O3�!w�PG��|�]ۏ�r���:���(t���a&��B�-�K��,$5��������45�E5���f���S�      �   O   x�uͻ�0��:���u�,�?��;����6e�.���ż�G������nQ����gq$����|� ��`R      �   8   x�340�4202�54�54�45�240F1�� D, "�("&@3��Ȝ=...  ��      �   �   x�UO�N�0����?`�h�֢�CUR��䊸���݂�zH�g9/���Qi��.�D�8�>v�����I�)�a�&�0v%���Yu]����77�W�#�[���)�R7��+�,�v�Zk1C3���*��"��4��x�o��gۉc��a�5�����1<�\R��.�CyZě?��b>V1k�M|������_�{�A��J��y�W@)%f(�!c��i�      �   �   x�U��j�0����)����r�	�Rⶤ�B/�=�J��iڷ�������>5(�����_�h�G���8^���G��O���.zxHm�!r,DY�������q�M8��	�����yX9�ҡ�JQ`.2��C�����`;�Μ"��H�,�+��A)%��"K�Am���!���u�c ��}��
�-Q�=dX@��o�7#ߛ@�Z�-x��������BY���*Q�ao�1���f8      �   Y   x�3�
300�JL�L-VKLN��K�4202�54�541��LcNC.#�r#\ʍP������ܘ�b�)��15�7#N�1W� �7J     