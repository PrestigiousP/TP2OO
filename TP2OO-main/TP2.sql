PGDMP                     
    x            TP2OO    13.0    13.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16394    TP2OO    DATABASE     c   CREATE DATABASE "TP2OO" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'French_Canada.1252';
    DROP DATABASE "TP2OO";
                postgres    false            �            1259    16408    cours    TABLE     �   CREATE TABLE public.cours (
    coursid integer NOT NULL,
    name character varying(100)[],
    sigle character varying(15)[],
    description text
);
    DROP TABLE public.cours;
       public         heap    postgres    false            �            1259    16395    etudiant    TABLE     �   CREATE TABLE public.etudiant (
    etudiantid integer NOT NULL,
    fname character varying(25)[],
    lname character varying(25)[],
    age integer
);
    DROP TABLE public.etudiant;
       public         heap    postgres    false            �            1259    16403    inscription    TABLE     �   CREATE TABLE public.inscription (
    inscriptionid integer NOT NULL,
    etudiantid integer,
    coursid integer,
    date timestamp with time zone
);
    DROP TABLE public.inscription;
       public         heap    postgres    false            �          0    16408    cours 
   TABLE DATA           B   COPY public.cours (coursid, name, sigle, description) FROM stdin;
    public          postgres    false    202   �       �          0    16395    etudiant 
   TABLE DATA           A   COPY public.etudiant (etudiantid, fname, lname, age) FROM stdin;
    public          postgres    false    200          �          0    16403    inscription 
   TABLE DATA           O   COPY public.inscription (inscriptionid, etudiantid, coursid, date) FROM stdin;
    public          postgres    false    201   *       /           2606    16415    cours cours_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.cours
    ADD CONSTRAINT cours_pkey PRIMARY KEY (coursid);
 :   ALTER TABLE ONLY public.cours DROP CONSTRAINT cours_pkey;
       public            postgres    false    202            +           2606    16402    etudiant etudiant_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.etudiant
    ADD CONSTRAINT etudiant_pkey PRIMARY KEY (etudiantid);
 @   ALTER TABLE ONLY public.etudiant DROP CONSTRAINT etudiant_pkey;
       public            postgres    false    200            -           2606    16407    inscription inscription_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.inscription
    ADD CONSTRAINT inscription_pkey PRIMARY KEY (inscriptionid);
 F   ALTER TABLE ONLY public.inscription DROP CONSTRAINT inscription_pkey;
       public            postgres    false    201            �      x������ � �      �      x������ � �      �      x������ � �     